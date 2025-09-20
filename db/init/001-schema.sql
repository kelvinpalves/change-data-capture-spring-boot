create table if not exists orders (
    id bigserial primary key,
    customer_name text not null,
    status text not null,
    total_cents bigint not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create or replace function set_updated_at()
returns trigger as $$
begin
  new.updated_at = now();
return new;
end;
$$ language plpgsql;

drop trigger if exists trg_orders_updated on orders;
create trigger trg_orders_updated
    before update on orders
    for each row execute procedure set_updated_at();

do $$
begin
  if not exists (select 1 from pg_roles where rolname = 'debezium') then
create role debezium login password 'debezium';
end if;
end$$;

grant connect on database appdb to debezium;
grant usage on schema public to debezium;
grant select on all tables in schema public to debezium;
alter default privileges in schema public grant select on tables to debezium;

alter role debezium replication;
