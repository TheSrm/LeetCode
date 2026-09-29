SELECT (
           SELECT DISTINCT salary
           FROM Employee
           ORDER BY salary DESC
           LIMIT 1 OFFSET 1 -- Saltate el primero, dice el offset, y limita a 1 el resultado que devuelves  
    ) AS SecondHighestSalary; -- Para evitar que cuando solo haya un salario, o todos iguales, devuelva una fila con null
