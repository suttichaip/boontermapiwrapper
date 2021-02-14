### BoonTermAPIWrapper


เป็น Library สำหรับการเรียกใช้งาน APIs ของบุญเติม ทั้ง API สำหรับทำธุรกรรมทางการเงิน และระบบบริการอื่นๆได้แก่
- SMS OTP Service สำหรับส่งข้อความไปยังเลขหมายปลายทางที่ต้องการ
- ระบบ Payment ได้แก่บริการการเติมเงินและชำระสินค้าค่าบริการต่างๆ
- Blacklist สำหรับตรวจสอบข้อมูลสภาพทางการเงินในทางกฎหมายของลูกค้า

## ระบบ payment แบ่งรูปแบบของการขั้นตอนการเรียกใช้ lib  เป็น 3 กลุ่ม
1. **one step to completed** ใช้ขั้นตอนเดียวให้การทำรายการ ได้แก่ service ดังนี้คือ
    - CASHCARD-­TRUE-­MONEY­E-­PIN
    - CASHCARD-12CALL
    - CASHCARD-PREPAID-TOT
    - GAME-STEAM-WALLET
    - GAME-MOL-POINT
    - GAME-COOKIE
    - GAME-JAM-CARD
    - GAME-A-CASH
    - GAME-LINE-CARD
    - GAME-DOT-ARENA
    - GAME-TOP-ELEVEN
    - GAME-EX-CASH
    - GAME-ZEST-CARD
    - PREPAID-AIS
    - PREPAID-DTAC
    - PREPAID-TRUE-MOVE
    - PREPAID-CAT-MY
    - PREPAID-I-MOBILE-3GX
    - PREPAID-TOT-3G
    - PREPAID-MYWORLD
    - PREPAID-WHITESPACE
    - WIFI-3BB
    - WIFI-TRUE
    - WIFI-TOT
    - INSURE-TUNE

2. **two step to completed** ใช้ 2 ขั้นตอนในการทำรายการคือ ขั้นตอนแรกจะเรียก function เพื่อขอข้อมูลที่ต้องใช้ในขั้นตอนที่สองมาก่อน แล้วนำข้อมูบที่ได้จากขั้นตอนแรกไปใช้ในการเรียก function ของขั้นตอนที่สอง โดยจะแตกต่างกันที่ขั้นตอนแรกโดยจะแบ่งเป็น 4 รูปแบบย่อยคือ

    2.1 **BILL-POSTPAID-AIS** ส่ง*หมายเลขโทรศัพท์ที่จะชำระค่าบริการ*และหมายเลขบัตรประชาชน มาเป็น List ใน paramenter ที่ชื่อ options ของ function

    2.2 **BILL-AIR-NET** ส่ง*หมายเลขโทรศัพท์ที่จะรับ SMS* พร้อมหมายเลขบัตรประชาชนและ Ref มาเป็น List ใน paramenter ชื่อ options ของ function

    2.3 **BILL-POSTPAID-TRUE** ส่งหมายเลขบัตรประชาชนพร้อม *หมายเลขโทรศัพท์ที่จะชำระค่าบริการ*หรือ Barcode มาเป็น List ใน paramenter ชื่อ options ของ function

    2.4 **BILL-PEA, BILL-PWA, BILL-MEA** และ **BILL-MWA** ส่งหมายเลขบัตรประชาชนพร้อม *หมายเลขโทรศัพท์ที่จะรับ SMS* และ Barcode มาเป็น List ใน paramenter ชื่อ options ของ function

Codedeploy -1
