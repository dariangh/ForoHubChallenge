create table perfil(
    id bigInt not null auto_increment,
    nombre varchar(100) not null,
    primary key (id)
);
create table curso(
                            id bigInt not null auto_increment,
                            nombre varchar(100) not null,
                            categoria varchar(100) not null,
                            primary key (id)
);
create table usuario(
                            id bigInt not null auto_increment,
                            nombre varchar(100) not null,
                            correo_electronico varchar(100) not null unique,
                            contrasena varchar(300) not null,
                            perfil_id BigInt,
                            primary key (id),
                            constraint fk_perfil foreign key (perfil_id) references perfil(id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE


);
CREATE TABLE topico(
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       titulo varchar(100) not null,
                       mensaje TEXT NOT NULL,
                       fecha_creacion DATETIME NOT NULL,
                       status TINYINT NOT NULL,
                       usuario_id BIGINT,
                       curso_id BIGINT,
                       primary key (id),

                       CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
                       ON DELETE CASCADE
                           ON UPDATE CASCADE,
                       CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
                       ON DELETE CASCADE
                       ON UPDATE CASCADE



);
CREATE TABLE respuesta (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           mensaje TEXT NOT NULL,
                           fecha_creacion DATETIME NOT NULL,
                           usuario_id BIGINT,
                           topico_id BIGINT,
                           primary key (id),

                           CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
                               ON DELETE CASCADE
                               ON UPDATE CASCADE,
                           CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topico(id)
                               ON DELETE CASCADE
                               ON UPDATE CASCADE


);


