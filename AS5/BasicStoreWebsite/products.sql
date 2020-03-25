USE web_store;

DROP TABLE IF EXISTS products;
 -- remove table if it already exists and start from scratch

CREATE TABLE products (
	productid CHAR(6),
    name CHAR(64),
    imagefile CHAR(64),
    price DECIMAL(6,2)
);

INSERT INTO products VALUES
	("HC","Classic Hoodie","Hoodie.png",40),
    ("HLE", "Limited Edition Hood","LimitedEdHood.png",54.95),
	("TC", "Classic Tee","TShirt.png",13.95),
	("TS","Seal Tee","SealTShirt.png",19.95),
	("TLJa","Japanese Tee","JapaneseTShirt.png",17.95),
	("TLKo","Korean Tee","KoreanTShirt.png",17.95),
	("TLCh","Chinese Tee","ChineseTShirt.png",17.95),
	("TLHi","Hindi Tee","HindiTShirt.png",17.95),
	("TLAr","Arabic Tee","ArabicTShirt.png",17.95),
	("TLHe","Hebrew Tee","HebrewTShirt.png",17.95),
	("AKy","Keychain","Keychain.png",2.95),
	("ALn","Lanyard","Lanyard.png",5.95),
	("ATherm","Thermos","Thermos.png",19.95),
	("AMinHm","Mini Football Helmet","MiniHelmet.png",29.95)
