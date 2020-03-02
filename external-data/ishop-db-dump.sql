--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4


--
-- Таблица аккаунтов
--

CREATE TABLE account (
    id integer NOT NULL,
    name character varying(60) NOT NULL,
    email character varying(100) NOT NULL
);


--
-- Id account
--

CREATE SEQUENCE account_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Таблица категорий товаров

CREATE TABLE category (
    id integer NOT NULL,
    name character varying(60) NOT NULL,
    url character varying(60) NOT NULL,
    product_count integer DEFAULT 0 NOT NULL
);


--
-- Таблица заказов

CREATE TABLE "order" (
    id bigint NOT NULL,
    id_account integer NOT NULL,
    created timestamp(0) without time zone DEFAULT now() NOT NULL
);


--
-- Таблица товаров в заказе

CREATE TABLE order_item (
    id bigint NOT NULL,
    id_order bigint NOT NULL,
    id_product integer NOT NULL,
    count integer NOT NULL
);


--
-- id товара в заказе

CREATE SEQUENCE order_item_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- id заказ

CREATE SEQUENCE order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Таблица производителей товара

CREATE TABLE producer (
    id integer NOT NULL,
    name character varying(60) NOT NULL,
    product_count integer DEFAULT 0 NOT NULL
);


--
-- Таблица продуктов магазина

CREATE TABLE product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description text NOT NULL,
    image_link character varying(255) NOT NULL,
    price numeric(8,2) NOT NULL,
    id_category integer NOT NULL,
    id_producer integer NOT NULL
);


--
-- TOC entry 2018 (class 2606 OID 16433)
-- Name: account_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_email_key UNIQUE (email);


--
-- TOC entry 2020 (class 2606 OID 16431)
-- Name: account_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 2010 (class 2606 OID 16400)
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 16402)
-- Name: category_url_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_url_key UNIQUE (url);


--
-- TOC entry 2024 (class 2606 OID 16449)
-- Name: order_item_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 16439)
-- Name: order_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 16408)
-- Name: producer_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY producer
    ADD CONSTRAINT producer_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 16416)
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2027 (class 2606 OID 16440)
-- Name: order_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk FOREIGN KEY (id_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2028 (class 2606 OID 16450)
-- Name: order_item_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_fk FOREIGN KEY (id_order) REFERENCES "order"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2029 (class 2606 OID 16455)
-- Name: order_item_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_fk1 FOREIGN KEY (id_product) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2025 (class 2606 OID 16417)
-- Name: product_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_fk FOREIGN KEY (id_category) REFERENCES category(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2026 (class 2606 OID 16422)
-- Name: product_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_fk1 FOREIGN KEY (id_producer) REFERENCES producer(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--