INSERT INTO device_info(screen_width, screen_height, screen_dpi, os_name, os_version, app_name, app_version, name, id_str, update_ts, file_name)
VALUES(1280, 752, 160, 'Browser', '1.0', 'abb', '1.0', 'Browser', 'test@comp', 1562313232, 'file1');

INSERT INTO get_pages(device_info_id, edition_def_id, publication_date)
VALUES(1, 11, '2017-06-06');