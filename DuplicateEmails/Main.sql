Select  distinct p.email as Email
from Person p join Person s on p.email=s.email
where p.id != s.id;