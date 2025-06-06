from flask import Flask, request, send_file
import pdfkit
import io
import os

app = Flask(__name__)

@app.route("/generate", methods=["POST"])
def generate_pdf():
    data = request.get_json()

    html_content = data.get("html", "")
    options = data.get("options", {})

    # Configurar wkhtmltopdf (ruta estándar en Linux)
    config = pdfkit.configuration(wkhtmltopdf="/usr/bin/wkhtmltopdf")

    # Generar el PDF
    pdf_bytes = pdfkit.from_string(html_content, False, configuration=config, options=options)
    pdf_output = io.BytesIO(pdf_bytes)

    return send_file(pdf_output, mimetype='application/pdf', as_attachment=True, download_name="documento.pdf")

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 8080))
    app.run(debug=True, host="0.0.0.0", port=port)
