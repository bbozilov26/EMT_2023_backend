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