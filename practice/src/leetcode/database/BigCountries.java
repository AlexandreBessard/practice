package leetcode.database;
//https://leetcode.com/problems/big-countries/
public class BigCountries {
/*
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| name        | varchar |
| continent   | varchar |
| area        | int     |
| population  | int     |
| gdp         | int     |
+-------------+---------+
name is the primary key column for this table.
Each row of this table gives information about the name of a country,
the continent to which it belongs, its area, the population, and its GDP value.


Input:
World table:
+-------------+-----------+---------+------------+--------------+
| name        | continent | area    | population | gdp          |
+-------------+-----------+---------+------------+--------------+
| Afghanistan | Asia      | 652230  | 25500100   | 20343000000  |
| Albania     | Europe    | 28748   | 2831741    | 12960000000  |
| Algeria     | Africa    | 2381741 | 37100000   | 188681000000 |
| Andorra     | Europe    | 468     | 78115      | 3712000000   |
| Angola      | Africa    | 1246700 | 20609294   | 100990000000 |
+-------------+-----------+---------+------------+--------------+
Output:
+-------------+------------+---------+
| name        | population | area    |
+-------------+------------+---------+
| Afghanistan | 25500100   | 652230  |
| Algeria     | 37100000   | 2381741 |
+-------------+------------+---------+


A country is big if:

it has an area of at least three million (i.e., 3000000 km2), or
it has a population of at least twenty-five million (i.e., 25000000).

Write an SQL query to report the name, population, and area of the big countries.

Return the result table in any order.

 */
    public static void main(String[] args) {
        /*
        The UNION operator is used to combine the result-set of two or more SELECT statements.

        Every SELECT statement within UNION must have the same number of columns
        The columns must also have similar data types
        The columns in every SELECT statement must also be in the same order
         */


        /*
        SELECT
        name, population, area   (with UNION -> must have same number of columns 3)
    FROM
        world
    WHERE
        area >= 3000000

    UNION    (combine the result-set of two or more SELECT statements)

    SELECT
        name, population, area   (with UNION -> must have same number of columns 3)
    FROM
        world
    WHERE
        population >= 25000000
    ;
         */
    }



}
