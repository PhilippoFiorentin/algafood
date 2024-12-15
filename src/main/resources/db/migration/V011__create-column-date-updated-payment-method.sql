alter table payment_method add date_updated datetime null;
update payment_method set date_updated = utc_timestamp;
alter table payment_method modify date_updated datetime not null;