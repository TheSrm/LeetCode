# 3498. Reverse Degree of a String

[Enlace al problema](https://leetcode.com/problems/reverse-degree-of-a-string/?envType=daily-question&envId=2026-09-20)

Given a string `s`, calculate its **reverse degree**.

The reverse degree is calculated as follows:

* Each character has a value according to the reversed alphabet:

  * `a = 26`
  * `b = 25`
  * `...`
  * `z = 1`
* Multiply the value of each character by its position in the string.
* Positions start from `1`.
* Add all the products together.

## Example 1

```text
Input: s = "abc"
Output: 148
```

| Character | Value in the reversed alphabet | Position | Product |
| :-------: | :----------------------------: | :------: | :-----: |
|    `a`    |               26               |     1    |    26   |
|    `b`    |               25               |     2    |    50   |
|    `c`    |               24               |     3    |    72   |

```text
26 + 50 + 72 = 148
```

## Example 2

```text
Input: s = "zaza"
Output: 160
```

| Character | Value in the reversed alphabet | Position | Product |
| :-------: | :----------------------------: | :------: | :-----: |
|    `z`    |                1               |     1    |    1    |
|    `a`    |               26               |     2    |    52   |
|    `z`    |                1               |     3    |    3    |
|    `a`    |               26               |     4    |   104   |

```text
1 + 52 + 3 + 104 = 160
```

## Constraints

* `1 <= s.length <= 1000`
* `s contains only lowercase English letters.`
