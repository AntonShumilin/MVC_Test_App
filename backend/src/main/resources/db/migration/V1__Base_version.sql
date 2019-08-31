create table users
(
	id bigserial not null
		constraint users_pkey
			primary key,
	createdat timestamp,
	deletedat timestamp,
	email varchar(255)
		constraint uk_6dotkott2kjsp8vw4d0m25fb7
			unique,
	firstname varchar(255),
	lastname varchar(255),
	password varchar(255),
	updatedat timestamp
);

alter table users owner to postgres;

create table receipts
(
	id bigserial not null
		constraint receipts_pkey
			primary key,
	addresstocheckfiscalsign varchar(255),
	authorityuri varchar(255),
	babuyeraddress varchar(255),
	cashtotalsum varchar(255),
	datetime timestamp,
	ecashtotalsum varchar(255),
	fiscaldocumentnumber varchar(255),
	fiscaldrivenumber varchar(255),
	fiscalsign varchar(255),
	kktregid varchar(255),
	name varchar(255),
	nds10 varchar(255),
	nds18 varchar(255),
	operationtype varchar(255),
	operator varchar(255),
	rawdata text,
	receiptcode varchar(255),
	requestnumber varchar(255),
	retailplaceaddress varchar(255),
	senderaddress varchar(255),
	shiftnumber varchar(255),
	taxationtype varchar(255),
	totalsum varchar(255),
	tsp_user_name varchar(255),
	userid bigint,
	userinn varchar(255)
);

alter table receipts owner to postgres;

create table items
(
	id bigserial not null
		constraint items_pkey
			primary key,
	datetime timestamp,
	modifiers varchar(255),
	name varchar(255),
	nds0 varchar(255),
	nds10 varchar(255),
	nds18 varchar(255),
	ndscalculated10 varchar(255),
	ndscalculated18 varchar(255),
	ndsno varchar(255),
	price varchar(255),
	quantity varchar(255),
	storno varchar(255),
	sum varchar(255),
	receiptid bigint
		constraint fki465l6urgeykleklk6699kt3c
			references receipts
);

alter table items owner to postgres;


