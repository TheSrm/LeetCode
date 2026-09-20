import json
import re
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
README = ROOT / "README.md"

START_MARKER = "<!-- LEETCODE_TABLE_START -->"
END_MARKER = "<!-- LEETCODE_TABLE_END -->"

IGNORED_DIRECTORIES = {
    ".git",
    ".github",
    "scripts",
    "__pycache__",
}


def camel_to_title(name):
    """
    Convert CamelCase to a readable title.
    """
    name = re.sub(r"([a-z0-9])([A-Z])", r"\1 \2", name)
    name = re.sub(r"([A-Z]+)([A-Z][a-z])", r"\1 \2", name)
    name = re.sub(r"([A-Za-z])([0-9])", r"\1 \2", name)
    name = re.sub(r"([0-9])([A-Za-z])", r"\1 \2", name)

    return name


def camel_to_slug(name):
    """
    Convert CamelCase to a LeetCode-style slug.
    """
    title = camel_to_title(name)
    return re.sub(r"[^a-zA-Z0-9]+", "-", title).strip("-").lower()


def get_leetcode_data(slug):
    """
    Get ID, title, and difficulty from LeetCode's GraphQL API.
    """
    query = """
    query questionData($titleSlug: String!) {
        question(titleSlug: $titleSlug) {
            questionFrontendId
            title
            titleSlug
            difficulty
        }
    }
    """

    payload = json.dumps({
        "query": query,
        "variables": {
            "titleSlug": slug
        }
    }).encode("utf-8")

    request = urllib.request.Request(
        "https://leetcode.com/graphql/",
        data=payload,
        headers={
            "Content-Type": "application/json",
            "User-Agent": "Mozilla/5.0",
            "Referer": "https://leetcode.com/",
        },
        method="POST",
    )

    try:
        with urllib.request.urlopen(request, timeout=15) as response:
            data = json.loads(response.read().decode("utf-8"))

        question = data.get("data", {}).get("question")

        if question:
            return {
                "id": question.get("questionFrontendId"),
                "title": question.get("title"),
                "slug": question.get("titleSlug"),
                "difficulty": question.get("difficulty"),
            }

    except Exception as error:
        print(f"Could not query LeetCode for '{slug}': {error}")

    return None


def find_problem_directories():
    """
    Find directories following the CamelCase convention.
    """
    directories = []

    for path in ROOT.iterdir():
        if not path.is_dir():
            continue

        if path.name in IGNORED_DIRECTORIES:
            continue

        if path.name.startswith("."):
            continue

        if not re.fullmatch(r"[A-Za-z0-9]+", path.name):
            continue

        directories.append(path)

    return directories


def build_table():
    problems = []

    for directory in find_problem_directories():
        folder_name = directory.name
        slug = camel_to_slug(folder_name)

        print(f"Processing: {folder_name}")
        print(f"LeetCode slug: {slug}")

        leetcode_data = get_leetcode_data(slug)

        if leetcode_data:
            problem_id = leetcode_data["id"]
            title = leetcode_data["title"]
            difficulty = leetcode_data["difficulty"]
            actual_slug = leetcode_data["slug"]
            url = f"https://leetcode.com/problems/{actual_slug}/"
        else:
            problem_id = "?"
            title = camel_to_title(folder_name)
            difficulty = "Unknown"
            url = f"https://leetcode.com/problems/{slug}/"

        # Intentamos convertir el ID a entero para ordenar numéricamente;
        # si falla (p. ej., si dio error la API), le asignamos un número muy alto.
        try:
            sort_key = int(problem_id)
        except ValueError:
            sort_key = float("inf")

        problems.append({
            "sort_key": sort_key,
            "id": problem_id,
            "title": title,
            "url": url,
            "difficulty": difficulty
        })

    # Ordenar por número de problema (sort_key)
    problems.sort(key=lambda x: x["sort_key"])

    # Construir las filas en Markdown
    rows = [
        f"| {p['id']} | [{p['title']}]({p['url']}) | {p['difficulty']} |"
        for p in problems
    ]

    table = [
        "| # | Problem | Difficulty |",
        "|---|---|---|",
        *rows,
    ]

    return "\n".join(table)


def update_readme():
    if not README.exists():
        raise FileNotFoundError("README.md was not found.")

    content = README.read_text(encoding="utf-8")

    if START_MARKER not in content:
        raise ValueError(f"Missing marker: {START_MARKER}")

    if END_MARKER not in content:
        raise ValueError(f"Missing marker: {END_MARKER}")

    start = content.index(START_MARKER) + len(START_MARKER)
    end = content.index(END_MARKER)

    table = build_table()

    new_content = (
            content[:start]
            + "\n"
            + table
            + "\n"
            + content[end:]
    )

    if new_content == content:
        print("README.md is already up to date.")
        return

    README.write_text(new_content, encoding="utf-8")
    print("README.md updated successfully.")


if __name__ == "__main__":
    update_readme()