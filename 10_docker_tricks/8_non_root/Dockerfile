FROM nginx:1.15.3

# setup configuration
COPY ./nginx.conf /etc/nginx/nginx.conf

# set proper access right
RUN touch /var/run/nginx.pid && \
  chown -R www-data:www-data /var/run/nginx.pid && \
  chown -R www-data:www-data /var/cache/nginx

# change user
USER www-data

# copy static html file
COPY index.html /var/www/htdocs/