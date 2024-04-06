CREATE TABLE public.post (
	id bigserial NOT NULL,
	"text" varchar(255) NULL,
	title varchar(255) NULL,
	CONSTRAINT post_pkey PRIMARY KEY (id)
);

CREATE TABLE public."comment" (
	id bigserial NOT NULL,
	"text" varchar(255) NULL,
	post_id int8 NULL,
	CONSTRAINT comment_pkey PRIMARY KEY (id)
);

INSERT INTO public.post
(id, "text", title)
VALUES(nextval('post_id_seq'::regclass),'title1', 'text1');

INSERT INTO public."comment"
(id, "text", post_id)
VALUES(1, 'comment1', 1);