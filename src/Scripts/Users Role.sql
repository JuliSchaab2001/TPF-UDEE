create user 'meter'@'%' identified by 'root12';
grant insert on measurements to 'meter';



create user 'employee'@'%' identified by 'root14';

grant execute on procedure get_consumes to 'employee';
grant execute on procedure top_ten_v1 to 'employee';

grant insert on clients to 'employee';
grant update on clients to 'employee';
grant delete on clients to 'employee';

grant insert on meters to 'employee';
grant update on meters to 'employee';
grant delete on meters to 'employee';

grant insert on tariffs to 'employee';
grant update on tariffs to 'employee';
grant delete on tariffs to 'employee';

grant insert on addresses to 'employee';
grant update on addresses to 'employee';
grant delete on addresses to 'employee';



create user 'client'@'%' identified by 'root';

grant execute on procedure get_consumes to 'client';

grant select on measurements to 'client';
grant select on bills to 'client';
grant select on clients to 'client';
grant select on addresses to 'client';
