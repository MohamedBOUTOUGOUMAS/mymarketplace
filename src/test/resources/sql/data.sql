SET REFERENTIAL_INTEGRITY FALSE;

delete from clients;
delete from products;
delete from turnovers;
delete from baskets;
delete from basketitems;

insert into clients(objectid, type, firstname, lastname, siret, tva, reason, enablediscountmaxamount)
values ('clientId1', 'PARTICULAR', 'Jhon', 'Doe', null, null, null, null);

insert into clients(objectid, type, firstname, lastname, siret, tva, reason, enablediscountmaxamount)
values ('clientId2', 'PROFESSIONAL', null, null, '123456789', '20', 'Prestation de service', 10000000);

insert into products(objectid, name, category, priceforparticular, priceforprofessional, priceforprofessionalafterdiscount)
values ('productId1', 'Iphone 16', 'PHONE_HIGH_END', 1500, 1150, 1000);

insert into products(objectid, name, category,  priceforparticular, priceforprofessional, priceforprofessionalafterdiscount)
values ('productId3', 'Iphone 16 pro', 'PHONE_HIGH_END', 1500, 1150, 1000);

insert into products(objectid, name, category,  priceforparticular, priceforprofessional, priceforprofessionalafterdiscount)
values ('productId2', 'Nokia 3310', 'PHONE_MID_RANGE', 800, 600, 550);

insert into products(objectid, name, category,  priceforparticular, priceforprofessional, priceforprofessionalafterdiscount)
values ('productId4', 'MacBook', 'LAPTOP', 1200, 1000, 900);

insert into turnovers(objectid, reportingyear, amount, clientid)
values ('turnover1', '2024', '100000000', 'clientId2');

insert into baskets(objectid, clientid)
values ('basketId1', 'clientId1');

insert into basketitems(objectid, basketid, productid, quantity)
values ('item1', 'basketId1', 'productId1', 5);


SET REFERENTIAL_INTEGRITY TRUE;
