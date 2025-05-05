#!/usr/bin/env bash

apt-get update && apt-get install -y wget xz-utils libfontconfig libxrender1

wget https://github.com/wkhtmltopdf/packaging/releases/download/0.12.6-1/wkhtmltox_0.12.6-1.bionic_amd64.deb
dpkg -i wkhtmltox_0.12.6-1.bionic_amd64.deb

# Enlace simbólico (por si se necesita)
ln -s /usr/local/bin/wkhtmltopdf /usr/bin/wkhtmltopdf
