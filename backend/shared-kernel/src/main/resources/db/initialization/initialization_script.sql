CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

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

create or replace function find_privilege_id_by_name(_label text) returns text as
$$
declare
    _id text;

begin
    select id into _id
    from ur_privilege
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function create_privilege_if_not_exists(_privilege text, _label text) returns void as
$$
begin

    if not exists(select id from ur_privilege where privilege = _privilege) then
        insert into ur_privilege(id, privilege, label)
        values (uuid_generate_v4(), _privilege, _label);
    end if;

end

$$ language plpgsql;

select create_privilege_if_not_exists('Reads users', 'READ_USERS');
select create_privilege_if_not_exists('Manages users', 'MANAGE_USERS');
select create_privilege_if_not_exists('Reads orders', 'READ_ORDERS');
select create_privilege_if_not_exists('Manages orders', 'MANAGE_ORDERS');
select create_privilege_if_not_exists('Reads quiz', 'READ_QUIZ');
select create_privilege_if_not_exists('Manages quiz', 'MANAGE_QUIZ');
select create_privilege_if_not_exists('Reads daily check ins', 'READ_DAILY_CHECK_INS');
select create_privilege_if_not_exists('Manages daily check ins', 'MANAGE_DAILY_CHECK_INS');
select create_privilege_if_not_exists('Reads products', 'READ_PRODUCTS');
select create_privilege_if_not_exists('Manages products', 'MANAGE_PRODUCTS');

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

create or replace function create_role_privilege_if_not_exists(_role_id text, _privilege_id text) returns void as
$$
begin
    if not exists(select id from ur_role_privilege where ur_role_id = _role_id and ur_privilege_id = _privilege_id) then
        insert into ur_role_privilege(id, ur_role_id, ur_privilege_id)
        values (uuid_generate_v4(), _role_id, _privilege_id);
    end if;
end

$$ language plpgsql;

-- Super administrator privileges
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_USERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_USERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_DAILY_CHECK_INS'));

-- Administrator privileges
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));

select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));

-- Customer privileges
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

create or replace function create_person(_first_name text, _last_name text, _phone_number text, _address text) returns text as
$$
declare
    _id text;

begin

    insert into ur_person(id, date_created, date_modified, first_name, last_name, phone_number, address)
    values(uuid_generate_v4(), now(), now(), _first_name, _last_name, _phone_number, _address)
    returning id into _id;

    return _id;

end

$$ language plpgsql;

create or replace function create_user_if_not_exists(
    _email text,
    _password text,
    _first_name text,
    _last_name text,
    _phone_number text,
    _address text,
    _role_id text,
    _daily_check_ins text[],
    _credit_balance double precision,
    _streak integer) returns void as
$$
declare
    _user_id text;
    _person_id text;
    _daily_check_in text;
begin

    if not exists(select id from ur_user where email = lower(_email)) then
        _person_id = create_person(_first_name, _last_name, _phone_number, _address);

        insert into ur_user(id, email, password, enabled, date_created, date_modified, ur_person_id, credit_balance, ur_role_id, streak)
        values (uuid_generate_v4(), _email, _password, true, now(), now(), _person_id, _credit_balance, _role_id, _streak)
        returning id into _user_id;

        foreach _daily_check_in in array _daily_check_ins loop
                insert into mm_user_daily_check_in(id, claimed, date_created, date_modified, ur_user_id, mm_daily_check_in_id)
                values (uuid_generate_v4(), false, now(), now(), _user_id, find_daily_check_in_id_by_label(_daily_check_in));
            end loop;

    end if;

end

$$ language plpgsql;

select create_user_if_not_exists('superadmin@emt.io','$2a$12$jg1BFg9nPNh6CGvs8aLH8uctGdWrQFQYvQtZm3mza8OjXHWJv/rdi','EMT', 'Super Admin', '070000000',
                                 'Orce Nikolov 202, 2/61, 1020, Skopje, North Macedonia',
                                 find_role_id_by_name('ROLE_SUPER_ADMIN'), array[]::text[], null, null);
select create_user_if_not_exists('admin@emt.io','$2a$12$HOv3AukHWA7m.1pdDYuvXODuU2lq4c4GU4rveqLWHDS7v8zDYTERy','EMT', 'Admin', '071000000',
                                 'Orce Nikolov 202, 2/61, 1020, Skopje, North Macedonia',
                                 find_role_id_by_name('ROLE_ADMIN'), array[]::text[], null, null);
-- select create_user_if_not_exists('customer@emt.io','$2a$12$pKQiFD9LtQBCZwswOCRUZe.Q2yXRsMmfprZf7YkdZ0LKPdSI/5XiG','EMT', 'Customer', '072000000',
--                                  find_role_id_by_name('ROLE_CUSTOMER'), array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0);

create or replace function find_quiz_answer_id_by_description(_description text) returns text as
$$
declare
    _id text;

begin
    select id into _id
    from mm_quiz_answer
    where description = _description;

    return _id;
end
$$ language plpgsql;

create or replace function find_correct_answer_by_question_and_given_correct_answer(
    _question_id text,
    _correct_answer text) returns text as
$$
declare
    _id text;
    _quiz_answer_id text;

begin
    _quiz_answer_id = find_quiz_answer_id_by_description(_correct_answer);

    select id into _id
    from mm_quiz_question_answer
    where mm_quiz_question_id = _question_id and mm_quiz_answer_id = _quiz_answer_id;

    return _id;
end
$$ language plpgsql;

create or replace function create_quiz_answer_if_not_exist(_answer text) returns void as
$$
declare
       _id text;
begin

    if not exists(select id from mm_quiz_answer where description = _answer) then
        insert into mm_quiz_answer(id, description)
        values (uuid_generate_v4(), _answer)
        returning id into _id;
    end if;

end
$$ language plpgsql;

select create_quiz_answer_if_not_exist('Yes');
select create_quiz_answer_if_not_exist('No');
select create_quiz_answer_if_not_exist('Dynamic Host Control Protocol');
select create_quiz_answer_if_not_exist('Domain Host Configuration Protocol');
select create_quiz_answer_if_not_exist('Dynamic Host Configuration Protocol');
select create_quiz_answer_if_not_exist('Domain Host Control Protocol');
select create_quiz_answer_if_not_exist('Swift');
select create_quiz_answer_if_not_exist('Java');
select create_quiz_answer_if_not_exist('Python');
select create_quiz_answer_if_not_exist('C#');
select create_quiz_answer_if_not_exist('Increase processing speed');
select create_quiz_answer_if_not_exist('Provide fault tolerance');
select create_quiz_answer_if_not_exist('Enhance network security');
select create_quiz_answer_if_not_exist('Accelerate graphics rendering');


create or replace function create_quiz_question_if_not_exist(
    _question text,
    _reward double precision,
    _difficulty text,
    _correct_answer text,
    _given_answers text[]
    ) returns void as

$$
declare
    _id text;
    _given_answer text;
    _quiz_question_correct_answer_id text;
begin

    if not exists(select id from mm_quiz_question where question = _question) then
       insert into mm_quiz_question(id, question, reward, difficulty)
       values (uuid_generate_v4(), _question, _reward, _difficulty)
       returning id into _id;

       foreach _given_answer in array _given_answers loop
                insert into mm_quiz_question_answer(id, mm_quiz_question_id, mm_quiz_answer_id)
                values (uuid_generate_v4(), _id, find_quiz_answer_id_by_description(_given_answer));
               end loop;

       _quiz_question_correct_answer_id = find_correct_answer_by_question_and_given_correct_answer(_id, _correct_answer);

    end if;

    update mm_quiz_question
    set correct_mm_quiz_answer_id = _quiz_question_correct_answer_id
    where id = _id;

end
$$ language plpgsql;

select create_quiz_question_if_not_exist(
               'Can a firewall be used to enhance the security of a computer network?',
               1.0,
               'EASY',
               'Yes',
               array['Yes', 'No']
);
select create_quiz_question_if_not_exist(
               'Is it possible for a computer to have multiple IP addresses assigned to a single network interface?',
               3.0,
               'MEDIUM',
               'Yes',
               array['Yes', 'No']
);
select create_quiz_question_if_not_exist(
               'In a virtualized environment, does live migration refer to the process of moving a running virtual machine from one physical server to another without causing downtime?',
               5.0,
               'HARD',
               'Yes',
               array['Yes', 'No']
);
select create_quiz_question_if_not_exist('In networking, what does DHCP stand for?',
                1.0,
                'EASY',
                'Dynamic Host Configuration Protocol',
                array[
                    'Dynamic Host Control Protocol',
                    'Domain Host Configuration Protocol',
                    'Dynamic Host Configuration Protocol',
                    'Domain Host Control Protocol'
                    ]
);
select create_quiz_question_if_not_exist(
               'Which programming language is commonly used for building Android applications?',
               3.0,
               'MEDIUM',
               'Java',
               array[
                   'Swift',
                   'Java',
                   'Python',
                   'C#'
                   ]
);
select create_quiz_question_if_not_exist(
               'What is the purpose of RAID (Redundant Array of Independent Disks) in data storage?',
               5.0,
               'HARD',
               'Provide fault tolerance',
               array[
                   'Increase processing speed',
                   'Provide fault tolerance',
                   'Enhance network security',
                   'Accelerate graphics rendering'
                   ]
);