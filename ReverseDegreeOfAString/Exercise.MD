
# 3498. Reverse Degree of a String

[Enlace al problema](https://leetcode.com/problems/reverse-degree-of-a-string/?envType=daily-question&envId=2026-09-20)

Dada una cadena `s`, calcula su **grado inverso**.

El grado inverso se calcula de la siguiente manera:

- Cada carácter tiene un valor según el alfabeto invertido:
    - `a = 26`
    - `b = 25`
    - `...`
    - `z = 1`
- Se multiplica el valor de cada carácter por su posición en la cadena.
- Las posiciones comienzan desde `1`.
- Se suman todos los productos.

## Ejemplo 1

```text
Entrada: s = "abc"
Salida: 148
```

| Carácter | Valor en el alfabeto invertido | Posición | Producto |
|:--------:|:------------------------------:|:--------:|:--------:|
| `a`      | 26                             | 1        | 26       |
| `b`      | 25                             | 2        | 50       |
| `c`      | 24                             | 3        | 72       |

```text
26 + 50 + 72 = 148
```

## Ejemplo 2

```text
Entrada: s = "zaza"
Salida: 160
```

| Carácter | Valor en el alfabeto invertido | Posición | Producto |
|:--------:|:------------------------------:|:--------:|:--------:|
| `z`      | 1                              | 1        | 1        |
| `a`      | 26                             | 2        | 52       |
| `z`      | 1                              | 3        | 3        |
| `a`      | 26                             | 4        | 104      |

```text
1 + 52 + 3 + 104 = 160
```

## Restricciones

- `1 <= s.length <= 1000`
- `s` contiene únicamente letras minúsculas del alfabeto inglés.
1 <= s.length <= 1000
s contains only lowercase English letters. 