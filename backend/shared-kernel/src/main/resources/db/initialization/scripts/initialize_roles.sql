create or replace function find_role_id_by_name(_label text) returns text as
$$
declare
    _id text;

begin
    select id into _id
    from ur_role
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function create_role_if_not_exists(_role text, _label text) returns void as
$$
begin
    if not exists(select id from ur_role where role = _role) then
        insert into ur_role(id, role, label)
        values (uuid_generate_v4(), _role, _label);
    end if;
end

$$ language plpgsql;

select create_role_if_not_exists('Super administrator', 'ROLE_SUPER_ADMIN');
select create_role_if_not_exists('Administrator', 'ROLE_ADMIN');
select create_role_if_not_exists('Customer', 'ROLE_CUSTOMER');

