FROM python:3.6.5-alpine
WORKDIR /admin_containment
ADD . /admin_containment
# Install required packages
RUN set -e; \
    apk add --no-cache --virtual .build-deps \
    gcc \
    libc-dev \
    linux-headers \
    mariadb-dev \
    python3-dev \
    postgresql-dev \
    ;
RUN apk --update --upgrade add gcc musl-dev jpeg-dev zlib-dev libffi-dev cairo-dev pango-dev gdk-pixbuf-dev
COPY requirements.txt /admin_containment
RUN pip install -r requirements.txt
CMD ["python","app.py"]