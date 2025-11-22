--
-- PostgreSQL database dump
--

\restrict QTdK6VPITkEPaUmC6V6NCghrxgJed7tuIRcXSBqXujMdv7tJ9cDG9ibW8YOgneM

-- Dumped from database version 15.15 (Debian 15.15-1.pgdg13+1)
-- Dumped by pg_dump version 15.15 (Homebrew)

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
-- Name: cart_items; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.cart_items (
    quantity integer NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.cart_items OWNER TO rebuy_user;

--
-- Name: cart_items_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.cart_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_items_id_seq OWNER TO rebuy_user;

--
-- Name: cart_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.cart_items_id_seq OWNED BY public.cart_items.id;


--
-- Name: credit_transactions; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.credit_transactions (
    amount numeric(18,4) NOT NULL,
    balance_after numeric(18,4) NOT NULL,
    id bigint NOT NULL,
    occurred_at timestamp(6) without time zone NOT NULL,
    user_id bigint NOT NULL,
    type character varying(40) NOT NULL,
    description character varying(200)
);


ALTER TABLE public.credit_transactions OWNER TO rebuy_user;

--
-- Name: credit_transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.credit_transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_transactions_id_seq OWNER TO rebuy_user;

--
-- Name: credit_transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.credit_transactions_id_seq OWNED BY public.credit_transactions.id;


--
-- Name: email_verification_tokens; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.email_verification_tokens (
    used boolean NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    expires_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    user_id bigint NOT NULL,
    token character varying(64) NOT NULL
);


ALTER TABLE public.email_verification_tokens OWNER TO rebuy_user;

--
-- Name: email_verification_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.email_verification_tokens_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.email_verification_tokens_id_seq OWNER TO rebuy_user;

--
-- Name: email_verification_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.email_verification_tokens_id_seq OWNED BY public.email_verification_tokens.id;


--
-- Name: environmental_activities; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.environmental_activities (
    participant_limit integer,
    created_at timestamp(6) without time zone NOT NULL,
    end_at timestamp(6) without time zone,
    id bigint NOT NULL,
    start_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone NOT NULL,
    name character varying(120) NOT NULL,
    description character varying(500)
);


ALTER TABLE public.environmental_activities OWNER TO rebuy_user;

--
-- Name: environmental_activities_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.environmental_activities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.environmental_activities_id_seq OWNER TO rebuy_user;

--
-- Name: environmental_activities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.environmental_activities_id_seq OWNED BY public.environmental_activities.id;


--
-- Name: environmental_impacts; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.environmental_impacts (
    carbon_emission numeric(18,6),
    waste_generated numeric(18,6),
    water_usage numeric(18,6),
    id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.environmental_impacts OWNER TO rebuy_user;

--
-- Name: environmental_impacts_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.environmental_impacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.environmental_impacts_id_seq OWNER TO rebuy_user;

--
-- Name: environmental_impacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.environmental_impacts_id_seq OWNED BY public.environmental_impacts.id;


--
-- Name: order_items; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.order_items (
    line_amount numeric(18,4) NOT NULL,
    quantity integer NOT NULL,
    unit_price numeric(18,4) NOT NULL,
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.order_items OWNER TO rebuy_user;

--
-- Name: order_items_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.order_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_items_id_seq OWNER TO rebuy_user;

--
-- Name: order_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.order_items_id_seq OWNED BY public.order_items.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.orders (
    amount_paid numeric(18,4) NOT NULL,
    total_amount numeric(18,4) NOT NULL,
    total_credit_earned numeric(18,4) NOT NULL,
    total_credit_used numeric(18,4) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL,
    paid_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone NOT NULL,
    user_id bigint NOT NULL,
    status character varying(30) NOT NULL,
    contact_phone character varying(50),
    receiver_name character varying(120),
    address character varying(200),
    environment_score_gain numeric(18,4),
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['PENDING'::character varying, 'PAID'::character varying, 'CANCELLED'::character varying, 'COMPLETED'::character varying])::text[])))
);


ALTER TABLE public.orders OWNER TO rebuy_user;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO rebuy_user;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: participations; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.participations (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    is_reward_given boolean NOT NULL,
    proof_image_url character varying(500),
    status character varying(20) NOT NULL,
    activity_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT participations_status_check CHECK (((status)::text = ANY ((ARRAY['APPLIED'::character varying, 'PENDING_VERIFICATION'::character varying, 'VERIFIED'::character varying, 'REJECTED'::character varying, 'CANCELLED'::character varying])::text[])))
);


ALTER TABLE public.participations OWNER TO rebuy_user;

--
-- Name: participations_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.participations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.participations_id_seq OWNER TO rebuy_user;

--
-- Name: participations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.participations_id_seq OWNED BY public.participations.id;


--
-- Name: password_reset_tokens; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.password_reset_tokens (
    used boolean NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    expires_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    user_id bigint NOT NULL,
    token character varying(64) NOT NULL
);


ALTER TABLE public.password_reset_tokens OWNER TO rebuy_user;

--
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.password_reset_tokens_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.password_reset_tokens_id_seq OWNER TO rebuy_user;

--
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.password_reset_tokens_id_seq OWNED BY public.password_reset_tokens.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.products (
    eco_score numeric(18,4) NOT NULL,
    price numeric(18,4) NOT NULL,
    stock integer NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    category character varying(30) NOT NULL,
    manufacturer character varying(120),
    name character varying(120) NOT NULL,
    description character varying(255),
    image_url character varying(255),
    eco_base_score numeric(18,4),
    saved_co2kg numeric(18,4),
    saved_oil_ml numeric(18,4),
    saved_plasticg numeric(18,4),
    saved_waterl numeric(18,4),
    CONSTRAINT products_category_check CHECK (((category)::text = ANY ((ARRAY['FOOD'::character varying, 'HOUSEHOLD'::character varying, 'BEAUTY'::character varying, 'ELECTRONICS'::character varying, 'ETC'::character varying])::text[])))
);


ALTER TABLE public.products OWNER TO rebuy_user;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO rebuy_user;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role character varying(40) NOT NULL
);


ALTER TABLE public.user_roles OWNER TO rebuy_user;

--
-- Name: users; Type: TABLE; Schema: public; Owner: rebuy_user
--

CREATE TABLE public.users (
    credit_balance numeric(18,4) NOT NULL,
    email_verified boolean NOT NULL,
    environment_score numeric(18,4) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    phone character varying(20),
    username character varying(40) NOT NULL,
    email character varying(120) NOT NULL,
    password character varying(255) NOT NULL,
    total_saved_co2kg numeric(18,4) DEFAULT 0 NOT NULL,
    total_saved_waterl numeric(18,4) DEFAULT 0 NOT NULL,
    total_saved_oil_ml numeric(18,4) DEFAULT 0 NOT NULL,
    total_saved_plasticg numeric(18,4) DEFAULT 0 NOT NULL
);


ALTER TABLE public.users OWNER TO rebuy_user;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: rebuy_user
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO rebuy_user;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rebuy_user
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: cart_items id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.cart_items ALTER COLUMN id SET DEFAULT nextval('public.cart_items_id_seq'::regclass);


--
-- Name: credit_transactions id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.credit_transactions ALTER COLUMN id SET DEFAULT nextval('public.credit_transactions_id_seq'::regclass);


--
-- Name: email_verification_tokens id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.email_verification_tokens ALTER COLUMN id SET DEFAULT nextval('public.email_verification_tokens_id_seq'::regclass);


--
-- Name: environmental_activities id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.environmental_activities ALTER COLUMN id SET DEFAULT nextval('public.environmental_activities_id_seq'::regclass);


--
-- Name: environmental_impacts id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.environmental_impacts ALTER COLUMN id SET DEFAULT nextval('public.environmental_impacts_id_seq'::regclass);


--
-- Name: order_items id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.order_items ALTER COLUMN id SET DEFAULT nextval('public.order_items_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: participations id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.participations ALTER COLUMN id SET DEFAULT nextval('public.participations_id_seq'::regclass);


--
-- Name: password_reset_tokens id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.password_reset_tokens ALTER COLUMN id SET DEFAULT nextval('public.password_reset_tokens_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: cart_items; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.cart_items (quantity, created_at, id, product_id, updated_at, user_id) FROM stdin;
\.


--
-- Data for Name: credit_transactions; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.credit_transactions (amount, balance_after, id, occurred_at, user_id, type, description) FROM stdin;
\.


--
-- Data for Name: email_verification_tokens; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.email_verification_tokens (used, created_at, expires_at, id, updated_at, user_id, token) FROM stdin;
t	2025-11-22 18:32:43.167002	2025-11-23 18:32:43.166754	2	2025-11-22 18:33:04.070513	2	a9551441e19843c7aa55f9cc45d4ab53
t	2025-11-22 18:28:26.680655	2025-11-23 18:28:26.679499	1	2025-11-22 18:33:23.969487	1	83df37c27ebb404ca5960b4df393946b
t	2025-11-23 00:41:35.011754	2025-11-24 00:41:35.010791	3	2025-11-23 00:42:20.080821	4	1bb5b83f3b65464c90b4492ab78f6e79
t	2025-11-23 02:55:41.002567	2025-11-24 02:55:41.001397	4	2025-11-23 02:56:06.409045	5	6dfba74e23f948889896f11a942e636d
f	2025-11-23 03:12:11.49457	2025-11-24 03:12:11.494333	5	2025-11-23 03:12:11.49457	6	d8891ccc70de48869268ba1d66b85b36
\.


--
-- Data for Name: environmental_activities; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.environmental_activities (participant_limit, created_at, end_at, id, start_at, updated_at, name, description) FROM stdin;
30	2025-11-23 02:33:36.799276	2025-11-23 02:33:36.799276	1	2025-11-23 02:33:36.799276	2025-11-23 02:33:36.799276	한강 플로깅	한강공원에서 쓰레기를 주우며 걷는 활동입니다. 2시간 동안 진행되며, 장갑과 쓰레기봉투가 제공됩니다
20	2025-11-23 02:34:38.036208	2025-11-23 02:34:38.036208	2	2025-11-23 02:34:38.036208	2025-11-23 02:34:38.036208	북한산 정화활동	북한산 등산로 주변 환경 정화 활동입니다. 등산과 환경 보호를 함께 할 수 있습니다.
25	2025-11-23 02:36:24.628694	2025-11-23 02:36:24.628694	3	2025-11-23 02:36:24.628694	2025-11-23 02:36:24.628694	서울숲 쓰레기 분리수거	서울숲 공원에서 쓰레기를 줍고 올바르게 분리수거하는 활동입니다.
\.


--
-- Data for Name: environmental_impacts; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.environmental_impacts (carbon_emission, waste_generated, water_usage, id, product_id) FROM stdin;
\.


--
-- Data for Name: order_items; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.order_items (line_amount, quantity, unit_price, id, order_id, product_id) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.orders (amount_paid, total_amount, total_credit_earned, total_credit_used, created_at, id, paid_at, updated_at, user_id, status, contact_phone, receiver_name, address, environment_score_gain) FROM stdin;
\.


--
-- Data for Name: participations; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.participations (id, created_at, updated_at, is_reward_given, proof_image_url, status, activity_id, user_id) FROM stdin;
1	2025-11-23 02:56:41.710552	2025-11-23 03:12:48.307374	t	\N	VERIFIED	2	5
\.


--
-- Data for Name: password_reset_tokens; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.password_reset_tokens (used, created_at, expires_at, id, updated_at, user_id, token) FROM stdin;
t	2025-11-22 18:34:07.499789	2025-11-22 20:34:07.497737	1	2025-11-22 18:35:02.910218	1	572c7255746d4b76838ea699fe36d493
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.products (eco_score, price, stock, created_at, id, updated_at, category, manufacturer, name, description, image_url, eco_base_score, saved_co2kg, saved_oil_ml, saved_plasticg, saved_waterl) FROM stdin;
12.5000	18000.0000	120	2025-11-22 18:26:42.719881	1	2025-11-22 18:26:42.719881	HOUSEHOLD	EcoMakers	Reusable Eco Bottle	친환경 스테인리스 텀블러	https://example.com/img/bottle.jpg	\N	\N	\N	\N	\N
5.2000	8500.0000	300	2025-11-22 18:26:42.748991	2	2025-11-22 18:26:42.748991	FOOD	GreenFarm	Organic Snack Pack	무첨가 친환경 포장 간식 세트	https://example.com/img/snack.jpg	\N	\N	\N	\N	\N
25.0000	42000.0000	50	2025-11-22 18:26:42.752915	3	2025-11-22 18:26:42.752915	ELECTRONICS	SunCharge	Solar Powered Charger	태양광 휴대용 충전기	https://example.com/img/solar.jpg	\N	\N	\N	\N	\N
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.user_roles (user_id, role) FROM stdin;
1	ROLE_USER
2	ROLE_USER
3	ADMIN
4	USER
5	USER
6	USER
6	ADMIN
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: rebuy_user
--

COPY public.users (credit_balance, email_verified, environment_score, created_at, id, updated_at, phone, username, email, password, total_saved_co2kg, total_saved_waterl, total_saved_oil_ml, total_saved_plasticg) FROM stdin;
0.0000	t	0.0000	2025-11-22 18:32:43.146719	2	2025-11-22 18:33:04.072156	01022223333	hello	pwadsfdtest@example.com	$2a$10$Tar0bfY0UDlyad9ozhW3UuKPlUcaZL5Oq2dzWjE94Mq7MqjBlmXL6	0.0000	0.0000	0.0000	0.0000
0.0000	t	0.0000	2025-11-22 18:28:26.644554	1	2025-11-22 18:35:02.910543	01022223333	pwdtest	pwdtest@example.com	$2a$10$FsgDRVHP360.GGQhpc7M4.rYmev3k.miGL6/n3UqNxlOACLSW6Z9W	0.0000	0.0000	0.0000	0.0000
0.0000	t	0.0000	2025-11-23 00:41:34.966951	4	2025-11-23 00:42:20.082907	01012341234	jmin9011	jmin9011@gmail.com	$2a$10$xUZV8VT9e5UMwrCzbW0YTuq9O5FPbEeHeIUUVw2E0qSfmSEBR/4im	0.0000	0.0000	0.0000	0.0000
0.0000	t	0.0000	2025-11-23 00:29:22.158686	3	2025-11-23 00:29:22.158686	010-0000-0000	admin	admin@rebuy.com	$2a$10$8bo.cIAazu5ftMh3GuxuK.UZXZgQD4WQZ1gUhqH9LQ8p8P/lM3Q3K	0.0000	0.0000	0.0000	0.0000
0.0000	t	0.0000	2025-11-23 03:12:11.484873	6	2025-11-23 03:12:11.484873	010-1234-5678	admin2	admin2@rebuy.com	$2a$10$h7eStjZxhbhM3izb8V58rOzjixXFuwU/LoTk/0OIUg47qAu1XuitW	0.0000	0.0000	0.0000	0.0000
1000.0000	t	0.0000	2025-11-23 02:55:40.960711	5	2025-11-23 03:12:48.307583	01001010101	akalths	adfadf@asdf.com	$2a$10$rZrAkrAH6a4ZfuQm8opTf.szRhhaDEPlJadjxeJQ6BlmuQLyriaRC	0.0000	0.0000	0.0000	0.0000
\.


--
-- Name: cart_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.cart_items_id_seq', 1, false);


--
-- Name: credit_transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.credit_transactions_id_seq', 1, false);


--
-- Name: email_verification_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.email_verification_tokens_id_seq', 5, true);


--
-- Name: environmental_activities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.environmental_activities_id_seq', 3, true);


--
-- Name: environmental_impacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.environmental_impacts_id_seq', 1, false);


--
-- Name: order_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.order_items_id_seq', 1, false);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, false);


--
-- Name: participations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.participations_id_seq', 1, true);


--
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.password_reset_tokens_id_seq', 1, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.products_id_seq', 3, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rebuy_user
--

SELECT pg_catalog.setval('public.users_id_seq', 6, true);


--
-- Name: cart_items cart_items_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT cart_items_pkey PRIMARY KEY (id);


--
-- Name: cart_items cart_items_user_id_product_id_key; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT cart_items_user_id_product_id_key UNIQUE (user_id, product_id);


--
-- Name: credit_transactions credit_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.credit_transactions
    ADD CONSTRAINT credit_transactions_pkey PRIMARY KEY (id);


--
-- Name: email_verification_tokens email_verification_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.email_verification_tokens
    ADD CONSTRAINT email_verification_tokens_pkey PRIMARY KEY (id);


--
-- Name: email_verification_tokens email_verification_tokens_token_key; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.email_verification_tokens
    ADD CONSTRAINT email_verification_tokens_token_key UNIQUE (token);


--
-- Name: environmental_activities environmental_activities_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.environmental_activities
    ADD CONSTRAINT environmental_activities_pkey PRIMARY KEY (id);


--
-- Name: environmental_impacts environmental_impacts_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.environmental_impacts
    ADD CONSTRAINT environmental_impacts_pkey PRIMARY KEY (id);


--
-- Name: environmental_impacts environmental_impacts_product_id_key; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.environmental_impacts
    ADD CONSTRAINT environmental_impacts_product_id_key UNIQUE (product_id);


--
-- Name: order_items order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: participations participations_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.participations
    ADD CONSTRAINT participations_pkey PRIMARY KEY (id);


--
-- Name: password_reset_tokens password_reset_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.password_reset_tokens
    ADD CONSTRAINT password_reset_tokens_pkey PRIMARY KEY (id);


--
-- Name: password_reset_tokens password_reset_tokens_token_key; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.password_reset_tokens
    ADD CONSTRAINT password_reset_tokens_token_key UNIQUE (token);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: cart_items uk1vhvont0fdtramle6nmghntj7; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT uk1vhvont0fdtramle6nmghntj7 UNIQUE (user_id, product_id);


--
-- Name: participations uk_user_activity; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.participations
    ADD CONSTRAINT uk_user_activity UNIQUE (user_id, activity_id);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: participations fk166yf958qjqf8uoslyuk9e19p; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.participations
    ADD CONSTRAINT fk166yf958qjqf8uoslyuk9e19p FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: cart_items fk1re40cjegsfvw58xrkdp6bac6; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fk1re40cjegsfvw58xrkdp6bac6 FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: cart_items fk709eickf3kc0dujx3ub9i7btf; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fk709eickf3kc0dujx3ub9i7btf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: order_items fkbioxgbv59vetrxe0ejfubep1w; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: email_verification_tokens fki1c4mmamlb8keqt74k4lrtwhc; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.email_verification_tokens
    ADD CONSTRAINT fki1c4mmamlb8keqt74k4lrtwhc FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: environmental_impacts fki9ttjvhj5excfhlsapq2q8dg0; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.environmental_impacts
    ADD CONSTRAINT fki9ttjvhj5excfhlsapq2q8dg0 FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: participations fkij889wcpfcyegppul12vs0edk; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.participations
    ADD CONSTRAINT fkij889wcpfcyegppul12vs0edk FOREIGN KEY (activity_id) REFERENCES public.environmental_activities(id);


--
-- Name: credit_transactions fkjl0dwyeb8wag4j40g87uhvk8d; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.credit_transactions
    ADD CONSTRAINT fkjl0dwyeb8wag4j40g87uhvk8d FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: password_reset_tokens fkk3ndxg5xp6v7wd4gjyusp15gq; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.password_reset_tokens
    ADD CONSTRAINT fkk3ndxg5xp6v7wd4gjyusp15gq FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: order_items fkocimc7dtr037rh4ls4l95nlfi; Type: FK CONSTRAINT; Schema: public; Owner: rebuy_user
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fkocimc7dtr037rh4ls4l95nlfi FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- PostgreSQL database dump complete
--

\unrestrict QTdK6VPITkEPaUmC6V6NCghrxgJed7tuIRcXSBqXujMdv7tJ9cDG9ibW8YOgneM

