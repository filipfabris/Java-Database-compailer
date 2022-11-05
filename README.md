# Java-Database-compailer

## Main
* Inside package: `hr.fer.oprpp1.hw04.db.main`
* Class: `studentDB`

## Example:
```
> query jmbag = "0000000003"
Using index for record retrieval.
+============+========+========+===+
| 0000000003 | Bosnić | Andrea | 4 |
+============+========+========+===+
Records selected: 1
> query jmbag = "0000000003" AND lastName LIKE "B*"
+============+========+========+===+
| 0000000003 | Bosnić | Andrea | 4 |
+============+========+========+===+
Records selected: 1
> query jmbag = "0000000003" AND lastName LIKE "L*"
Records selected: 0
> query lastName LIKE "B*"
+============+===========+===========+===+
| 0000000002 | Bakamović | Petra | 3 |
| 0000000003 | Bosnić | Andrea | 4 |
| 0000000004 | Božić | Marin | 5 |
| 0000000005 | Brezović | Jusufadis | 2 |
+============+===========+===========+===+
Records selected: 4
> query lastName LIKE "Be*"
Records selected: 0
> exit
Goodbye!
```
