from flask import Flask, request, send_file
from weasyprint import HTML
import io

app = Flask(__name__)

@app.route("/generate", methods=["POST"])
def generate_pdf():
    html_content = request.get_data(as_text=True)
    pdf_bytes = HTML(string=html_content).write_pdf()
    return send_file(io.BytesIO(pdf_bytes), mimetype="application/pdf", as_attachment=True, download_name="documento.pdf")

# 👇 ESTA LÍNEA ES CLAVE PARA RENDER
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080)
