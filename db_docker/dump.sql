--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2022-11-08 20:17:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 122384)
-- Name: devices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.devices (
    id bigint NOT NULL,
    address character varying(255),
    description character varying(255),
    maximum_energy real,
    id_user bigint
);


ALTER TABLE public.devices OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 122383)
-- Name: devices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.devices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.devices_id_seq OWNER TO postgres;

--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 210
-- Name: devices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.devices_id_seq OWNED BY public.devices.id;


--
-- TOC entry 212 (class 1259 OID 122392)
-- Name: devices_timestamps; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.devices_timestamps (
    device_id bigint NOT NULL,
    timestamps_id_timestamp bigint NOT NULL
);


ALTER TABLE public.devices_timestamps OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 103507)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 122396)
-- Name: timestamp; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."timestamp" (
    id_timestamp bigint NOT NULL,
    consumption real NOT NULL,
    "time" timestamp without time zone,
    id_device bigint
);


ALTER TABLE public."timestamp" OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 122395)
-- Name: timestamp_id_timestamp_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.timestamp_id_timestamp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.timestamp_id_timestamp_seq OWNER TO postgres;

--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 213
-- Name: timestamp_id_timestamp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.timestamp_id_timestamp_seq OWNED BY public."timestamp".id_timestamp;


--
-- TOC entry 216 (class 1259 OID 122403)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id_user bigint NOT NULL,
    password character varying(255),
    user_role character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 122402)
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_user_seq OWNER TO postgres;

--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_user_seq OWNED BY public.users.id_user;


--
-- TOC entry 3179 (class 2604 OID 122387)
-- Name: devices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices ALTER COLUMN id SET DEFAULT nextval('public.devices_id_seq'::regclass);


--
-- TOC entry 3180 (class 2604 OID 122399)
-- Name: timestamp id_timestamp; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."timestamp" ALTER COLUMN id_timestamp SET DEFAULT nextval('public.timestamp_id_timestamp_seq'::regclass);


--
-- TOC entry 3181 (class 2604 OID 122406)
-- Name: users id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id_user SET DEFAULT nextval('public.users_id_user_seq'::regclass);


--
-- TOC entry 3335 (class 0 OID 122384)
-- Dependencies: 211
-- Data for Name: devices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.devices (id, address, description, maximum_energy, id_user) FROM stdin;
5	salaj	description 5	200	1
4	cluj	description 4	190	3
3	timisoara	description 3	178	1
6	gfddg	fdfdgd	1	\N
2	s	salut	1	3
\.


--
-- TOC entry 3336 (class 0 OID 122392)
-- Dependencies: 212
-- Data for Name: devices_timestamps; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.devices_timestamps (device_id, timestamps_id_timestamp) FROM stdin;
\.


--
-- TOC entry 3338 (class 0 OID 122396)
-- Dependencies: 214
-- Data for Name: timestamp; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."timestamp" (id_timestamp, consumption, "time", id_device) FROM stdin;
10	10	2022-10-25 22:00:00	5
11	40	2022-10-25 23:00:00	5
12	105	2022-10-27 08:00:00	5
\.


--
-- TOC entry 3340 (class 0 OID 122403)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id_user, password, user_role, username) FROM stdin;
2	admin	ADMIN	admin
3	12345	USER	raducaprita
1	1234	USER	mihaighise
\.


--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 210
-- Name: devices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.devices_id_seq', 6, true);


--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 209
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 213
-- Name: timestamp_id_timestamp_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.timestamp_id_timestamp_seq', 12, true);


--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_user_seq', 4, true);


--
-- TOC entry 3183 (class 2606 OID 122391)
-- Name: devices devices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices
    ADD CONSTRAINT devices_pkey PRIMARY KEY (id);


--
-- TOC entry 3187 (class 2606 OID 122401)
-- Name: timestamp timestamp_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."timestamp"
    ADD CONSTRAINT timestamp_pkey PRIMARY KEY (id_timestamp);


--
-- TOC entry 3185 (class 2606 OID 122412)
-- Name: devices_timestamps uk_a7xg870e413cv9bhxoahry1ou; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices_timestamps
    ADD CONSTRAINT uk_a7xg870e413cv9bhxoahry1ou UNIQUE (timestamps_id_timestamp);


--
-- TOC entry 3189 (class 2606 OID 122410)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 3190 (class 2606 OID 122413)
-- Name: devices fk2dft5kt6rgll8uwolwif5kpy0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices
    ADD CONSTRAINT fk2dft5kt6rgll8uwolwif5kpy0 FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 3191 (class 2606 OID 122418)
-- Name: devices_timestamps fkp60eoypyb0c309svqsau9htfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices_timestamps
    ADD CONSTRAINT fkp60eoypyb0c309svqsau9htfe FOREIGN KEY (timestamps_id_timestamp) REFERENCES public."timestamp"(id_timestamp);


--
-- TOC entry 3193 (class 2606 OID 122428)
-- Name: timestamp fkp86m0pgpsmimw8sx18vdt3guh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."timestamp"
    ADD CONSTRAINT fkp86m0pgpsmimw8sx18vdt3guh FOREIGN KEY (id_device) REFERENCES public.devices(id);


--
-- TOC entry 3192 (class 2606 OID 122423)
-- Name: devices_timestamps fkrrguui1bo3bk5jbc8d5ivh8bu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices_timestamps
    ADD CONSTRAINT fkrrguui1bo3bk5jbc8d5ivh8bu FOREIGN KEY (device_id) REFERENCES public.devices(id);


-- Completed on 2022-11-08 20:17:29

--
-- PostgreSQL database dump complete
--

