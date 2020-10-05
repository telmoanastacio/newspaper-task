DROP TABLE IF EXISTS device_info;

CREATE TABLE device_info(
    id bigint auto_increment not null,
    screen_width int not null,
    screen_height int not null,
    screen_dpi int not null,
    os_name varchar(255) not null,
    os_version varchar(255) not null,
    app_name varchar(255) not null,
    app_version varchar(255) not null,
    name varchar(255) not null,
    id_str varchar(255) not null,
    PRIMARY KEY (id));

DROP TABLE IF EXISTS get_pages;

CREATE TABLE get_pages(
    id bigint auto_increment not null,
    device_info_id bigint not null,
    edition_def_id varchar(255) not null,
    publication_date date not null,
    PRIMARY KEY (id)

    CONSTRAINT `FK_device_info` FOREIGN KEY (`device_info_id`)
    REFERENCES `device_info` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION);