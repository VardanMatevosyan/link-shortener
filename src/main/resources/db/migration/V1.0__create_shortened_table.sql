create schema if not exists bobocode;

create table if not exists bobocode.shortened_urls
(
    id varchar not null,
    original_url varchar not null,
    title varchar not null,
    created_at timestamp not null default now(),
    constraint shortened_urls_id_pk primary key (id)
);
