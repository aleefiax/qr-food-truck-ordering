import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";

const QRCodeGenerator = () => {
  const [qrCode, setQrCode] = useState<string | null>(null);

  useEffect(() => {
    const fetchQRCode = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/qr/generate",
          {
            responseType: "arraybuffer",
          }
        );

        const qrImage = `data:image/png;base64,${btoa(
          new Uint8Array(response.data).reduce(
            (data, byte) => data + String.fromCharCode(byte),
            ""
          )
        )}`;

        setQrCode(qrImage);
      } catch (error) {
        console.error("Failed to load QR Code", error);
      }
    };

    fetchQRCode();
  }, []);

  return (
    <div>
      <h2>Scan this QR Code</h2>
      {qrCode ? <img src={qrCode} alt="QR Code" /> : <p>Loading QR Code...</p>}
    </div>
  );
};

export default QRCodeGenerator;
