create or replace function find_daily_check_in_id_by_label(_label text) returns text as
$$
declare
    _id text;

begin
    select id into _id
    from mm_daily_check_in
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function create_daily_check_in_if_not_exists(_label text, _description text, _daily_reward double precision) returns void as
$$
begin
    if not exists(select id from mm_daily_check_in where label = _label) then
        insert into mm_daily_check_in(id, label, description, daily_reward)
        values (uuid_generate_v4(), _label, _description, _daily_reward);
    end if;
end

$$ language plpgsql;

select create_daily_check_in_if_not_exists('DAY_1', 'First day check in', 10.0);
select create_daily_check_in_if_not_exists('DAY_2', 'Second day check in', 25.0);
select create_daily_check_in_if_not_exists('DAY_3', 'Third day check in', 40.0);
select create_daily_check_in_if_not_exists('DAY_4', 'Fourth day check in', 55.0);
select create_daily_check_in_if_not_exists('DAY_5', 'Fifth day check in', 70.0);
select create_daily_check_in_if_not_exists('DAY_6', 'Sixth day check in', 85.0);
select create_daily_check_in_if_not_exists('DAY_7', 'Seventh day check in', 100.0);

