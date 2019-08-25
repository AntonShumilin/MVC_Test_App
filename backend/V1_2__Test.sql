create table test
(
    id                       bigserial not null
        constraint test_pkey
            primary key,
    test_name varchar(255)
);

alter table test
    owner to postgres;


