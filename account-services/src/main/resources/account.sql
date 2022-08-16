-- cassandra defined schema
CREATE KEYSPACE microservisticket
    WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

-- use cassandra schema
use microservisticket;

-- craete table example cassandra
CREATE TABLE emp(
                    emp_id int PRIMARY KEY,
                    emp_name text,
                    emp_city text,
                    emp_sal varint,
                    emp_phone varint
);

select * from emp;

create table accounts
(
    id text primary key,
    uname text,
    name text,
    surname text,
    email text,
    birth_date timestamp,
    pwd text,
    created_at timestamp,
    is_active boolean,
);
