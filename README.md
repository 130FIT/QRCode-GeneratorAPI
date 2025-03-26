# QR Code Versions

QRCode version หมายถึง ขนาดของกริด (grid) ที่ใช้ในการสร้าง QR Code ซึ่งจะกำหนดความสามารถในการเก็บข้อมูลใน QR Code นั้นๆ

## ความหมายของ QR Code Version

QRCode มีทั้งหมด 40 version โดยแต่ละ version จะมีขนาดกริดที่แตกต่างกัน โดยเริ่มจาก version 1 ถึง version 40 ดังนี้:

- **Version 1**: มีขนาดกริด 21x21
- **Version 2**: ขนาดกริด 25x25
- **Version 3**: ขนาดกริด 29x29
- **Version 4**: ขนาดกริด 33x33
- ...
- **Version 40**: ขนาดกริด 177x177

โดยแต่ละ version เพิ่มขึ้นจาก version ก่อนหน้า ซึ่งจะสามารถเก็บข้อมูลได้มากขึ้นตามขนาดของกริดที่เพิ่มขึ้น

## ความสามารถในการเก็บข้อมูล

- **Version 1**: สามารถเก็บข้อมูลได้ 25 ตัวอักษร (ในกรณีที่ใช้การเข้ารหัสแบบ Numeric)
- **Version 2**: เก็บข้อมูลได้มากขึ้นเป็น 47 ตัวอักษร
- **Version 40**: สามารถเก็บข้อมูลได้สูงสุดถึง 4,296 ตัวอักษร (ในการเข้ารหัสแบบ Numeric)

แต่ละ version จะมีระดับการแก้ไขข้อผิดพลาด (Error Correction Level) ที่แตกต่างกัน โดยใช้มาตรฐานการแก้ไขข้อผิดพลาด 4 ระดับ ได้แก่:

1. **L (Low)**: สามารถแก้ไขข้อผิดพลาดได้ 7%
2. **M (Medium)**: สามารถแก้ไขข้อผิดพลาดได้ 15%
3. **Q (Quartile)**: สามารถแก้ไขข้อผิดพลาดได้ 25%
4. **H (High)**: สามารถแก้ไขข้อผิดพลาดได้ 30%

## การเลือก Version ของ QR Code

เมื่อคุณสร้าง QR Code คุณสามารถเลือก version ตามจำนวนข้อมูลที่ต้องการเก็บ:

- หากข้อมูลมีขนาดเล็กและไม่ซับซ้อน ก็สามารถใช้ **version 1** ได้
- หากข้อมูลมีขนาดใหญ่ขึ้น และต้องการการแก้ไขข้อผิดพลาดที่สูงขึ้น อาจจะต้องเลือกใช้ **version 40**

โดย **QRCode** จะเลือก version ที่เหมาะสมตามขนาดของข้อมูลและระดับการแก้ไขข้อผิดพลาดที่คุณเลือก

## ตัวอย่างการสร้าง QR Code ด้วย Version ที่กำหนด

ในกรณีที่ต้องการกำหนด version ของ QR Code สามารถทำได้โดยการกำหนดขนาดของกริด เช่น:

```java
import java.util.HashMap;

QRCodeWriter qrCodeWriter = new QRCodeWriter();
HashMap<EncodeHintType, Object> hints = new HashMap<>();
hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // กำหนดระดับการแก้ไขข้อผิดพลาดเป็น H
hints.put(EncodeHintType.QR_VERSION, 5); // กำหนด version เป็น 5

BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300, hints);
```

# ตารางแสดงความสามารถของ QR Code Versions

| **Version** | **ขนาดกริด** | **เก็บข้อมูล (Numeric)** | **เก็บข้อมูล (Alphanumeric)** | **เก็บข้อมูล (Binary)** | **เก็บข้อมูล (Kanji)** |
|-------------|--------------|--------------------------|------------------------------|-------------------------|------------------------|
| 1           | 21x21        | 25 characters            | 17 characters                | 11 bytes                | 7 characters           |
| 2           | 25x25        | 47 characters            | 34 characters                | 22 bytes                | 14 characters          |
| 3           | 29x29        | 77 characters            | 58 characters                | 36 bytes                | 24 characters          |
| 4           | 33x33        | 114 characters           | 83 characters                | 58 bytes                | 38 characters          |
| 5           | 37x37        | 154 characters           | 115 characters               | 78 bytes                | 51 characters          |
| 6           | 41x41        | 195 characters           | 141 characters               | 97 bytes                | 65 characters          |
| 7           | 45x45        | 224 characters           | 164 characters               | 116 bytes               | 74 characters          |
| 8           | 49x49        | 279 characters           | 202 characters               | 139 bytes               | 88 characters          |
| 9           | 53x53        | 335 characters           | 242 characters               | 151 bytes               | 104 characters         |
| 10          | 57x57        | 395 characters           | 280 characters               | 178 bytes               | 120 characters         |
| 11          | 61x61        | 468 characters           | 314 characters               | 198 bytes               | 133 characters         |
| 12          | 65x65        | 535 characters           | 367 characters               | 224 bytes               | 146 characters         |
| 13          | 69x69        | 619 characters           | 408 characters               | 251 bytes               | 160 characters         |
| 14          | 73x73        | 667 characters           | 452 characters               | 272 bytes               | 172 characters         |
| 15          | 77x77        | 742 characters           | 493 characters               | 307 bytes               | 186 characters         |
| 16          | 81x81        | 813 characters           | 537 characters               | 341 bytes               | 200 characters         |
| 17          | 85x85        | 872 characters           | 586 characters               | 362 bytes               | 213 characters         |
| 18          | 89x89        | 976 characters           | 640 characters               | 396 bytes               | 226 characters         |
| 19          | 93x93        | 1,027 characters          | 698 characters               | 425 bytes               | 240 characters         |
| 20          | 97x97        | 1,151 characters          | 742 characters               | 453 bytes               | 253 characters         |
| 21          | 101x101      | 1,200 characters          | 791 characters               | 488 bytes               | 267 characters         |
| 22          | 105x105      | 1,242 characters          | 845 characters               | 522 bytes               | 280 characters         |
| 23          | 109x109      | 1,380 characters          | 899 characters               | 553 bytes               | 293 characters         |
| 24          | 113x113      | 1,451 characters          | 958 characters               | 586 bytes               | 305 characters         |
| 25          | 117x117      | 1,512 characters          | 1016 characters              | 610 bytes               | 317 characters         |
| 26          | 121x121      | 1,604 characters          | 1073 characters              | 644 bytes               | 330 characters         |
| 27          | 125x125      | 1,687 characters          | 1131 characters              | 668 bytes               | 342 characters         |
| 28          | 129x129      | 1,732 characters          | 1190 characters              | 703 bytes               | 354 characters         |
| 29          | 133x133      | 1,807 characters          | 1253 characters              | 732 bytes               | 367 characters         |
| 30          | 137x137      | 1,898 characters          | 1314 characters              | 766 bytes               | 380 characters         |
| 31          | 141x141      | 2,003 characters          | 1371 characters              | 803 bytes               | 393 characters         |
| 32          | 145x145      | 2,084 characters          | 1432 characters              | 832 bytes               | 406 characters         |
| 33          | 149x149      | 2,166 characters          | 1493 characters              | 866 bytes               | 419 characters         |
| 34          | 153x153      | 2,232 characters          | 1555 characters              | 895 bytes               | 432 characters         |
| 35          | 157x157      | 2,321 characters          | 1616 characters              | 929 bytes               | 445 characters         |
| 36          | 161x161      | 2,389 characters          | 1674 characters              | 958 bytes               | 458 characters         |
| 37          | 165x165      | 2,480 characters          | 1735 characters              | 992 bytes               | 471 characters         |
| 38          | 169x169      | 2,561 characters          | 1793 characters              | 1021 bytes              | 484 characters         |
| 39          | 173x173      | 2,645 characters          | 1853 characters              | 1054 bytes              | 497 characters         |
| 40          | 177x177      | 2,718 characters          | 1914 characters              | 1087 bytes              | 510 characters         |

## คำอธิบาย
- **Numeric**: จำนวนตัวเลขที่สามารถเก็บได้
- **Alphanumeric**: จำนวนตัวอักษรที่สามารถเก็บได้
- **Binary**: ขนาดของข้อมูลในรูปแบบไบนารี
- **Kanji**: จำนวนตัวอักษรในภาษาญี่ปุ่น (Kanji) ที่สามารถเก็บได้

ข้อมูลข้างต้นสามารถใช้เป็นตัวเลือกในการเลือกขนาดของ QR Code ที่เหมาะสมกับการใช้งานตามปริมาณข้อมูลที่ต้องการเก็บใน QR Code.
