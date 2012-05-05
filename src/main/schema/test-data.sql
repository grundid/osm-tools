CREATE TABLE relation (
    relation_id integer NOT NULL,
    relation_type text NOT NULL,
    name text NOT NULL,
    route text NOT NULL,
    ref text NOT NULL,
    network text NOT NULL,
    operator text NOT NULL
);

CREATE TABLE node_tags (
    node_id bigint NOT NULL,
    k text NOT NULL,
    v text NOT NULL
);

CREATE TABLE nodes (
    node_id bigint NOT NULL,
    version integer NOT NULL,
    lat bigint NOT NULL,
    lon bigint NOT NULL
);
