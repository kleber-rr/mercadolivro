CREATE TABLE purchase(
	id serial primary key,
    price decimal(10,2) not null,
    customer_id int not null,
    nfe varchar(255),
    created_at timestamp without time zone not null,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE purchase_book(
    purchase_id int not null,
    book_id int not null,
    FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    PRIMARY KEY(purchase_id, book_id)
);