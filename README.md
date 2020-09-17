Creacion de la Base de Datos
==========

Crear una base de datos en postgres con el nombre "fidelizacion", luego agregar las siguientes tablas:
~~~~sql
CREATE TABLE public.cliente
(
    id_cliente integer NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    apellido character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default",
    tipo_documento character varying(50) COLLATE pg_catalog."default",
    fecha_nacimiento date,
    telefono character varying(50) COLLATE pg_catalog."default",
    nacionalidad character varying(50) COLLATE pg_catalog."default",
    ci integer NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
);
CREATE TABLE public.bolsa_punto
(
    id_bolsa integer NOT NULL,
    id_cliente integer NOT NULL,
    fecha_asignacion date,
    fecha_caducidad date,
    puntaje_utilizado double precision,
    saldo_puntos double precision,
    monto_inicial double precision,
    CONSTRAINT bolsa_puntos_pkey PRIMARY KEY (id_bolsa),
    CONSTRAINT fk_1 FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE public.concepto_uso
(
    id_concepto integer NOT NULL,
    descripcion character varying(200) COLLATE pg_catalog."default",
    puntos double precision,
    CONSTRAINT "Conjunto_pkey" PRIMARY KEY (id_concepto)
);
CREATE TABLE public.reglas
(
    id_regla integer NOT NULL,
    inferior double precision NOT NULL,
    superior double precision NOT NULL,
    monto double precision NOT NULL,
    CONSTRAINT "Reglas_pkey" PRIMARY KEY (id_regla)
);
CREATE TABLE public.uso_cabecera
(
    id_cabecera integer NOT NULL,
    id_cliente integer NOT NULL,
    puntaje_utilizado double precision NOT NULL,
    fecha_uso date NOT NULL,
    CONSTRAINT uso_cabecera_pkey PRIMARY KEY (id_cabecera),
    CONSTRAINT fk_1 FOREIGN KEY (id_cabecera)
        REFERENCES public.cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
CREATE TABLE public.uso_detalle
(
    id_detalle integer NOT NULL,
    id_cabecera integer NOT NULL,
    puntaje_utilizado double precision NOT NULL,
    id_bolsa integer NOT NULL,
    CONSTRAINT uso_detalle_pkey PRIMARY KEY (id_detalle),
    CONSTRAINT foreign_bolsa FOREIGN KEY (id_detalle)
        REFERENCES public.bolsa_punto (id_bolsa) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT foreign_cabecera FOREIGN KEY (id_cabecera)
        REFERENCES public.uso_cabecera (id_cabecera) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE public.vencimiento
(
    id_vencimiento integer NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date NOT NULL,
    duracion integer NOT NULL,
    CONSTRAINT pkey PRIMARY KEY (id_vencimiento)
);
CREATE SEQUENCE public.bolsa_sec;
CREATE SEQUENCE public.cabecera_sec;
CREATE SEQUENCE public.cliente_sec;
CREATE SEQUENCE public.concepto_sec;
CREATE SEQUENCE public.detalle_sec;
CREATE SEQUENCE public.regla_sec;
CREATE SEQUENCE public.vencimiento_sec;
~~~~

# Configurar el WildFly:
Ir en la carpeta raiz del Wildfly, luego en la carpreta standalone\configuration, agregar en el archivo standalone.xml el siguiente datasource:
```

<datasource jndi-name="java:jboss/datasources/fidelizacion" pool-name="FidelizacionPostgres" enabled="true" use-java-context="true">
                    <connection-url>jdbc:postgresql://localhost:5432/fidelizacion</connection-url>
                    <driver>postgres</driver>
                    <pool>
                        <max-pool-size>20</max-pool-size>
                    </pool>
                    <security>
                        <user-name>postgres</user-name>
                        <password>postgres</password>
                    </security>
</datasource>
```
En el apartado de user-name y password agregar el usuario y contraseña de la cuenta del administrador del postgres
