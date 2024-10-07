alter table restaurant add open tinyint(1) not null;
update algafood.restaurant set open=false;