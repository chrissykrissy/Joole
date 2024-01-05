# Online Shopping Mall REST API
_"xsy" branch contains security with jwt_
<br>
Product side could be mainly divided into three parts 1) Product, 2) Product Type, 3)Technical Detail
1.  Product consists all the information about the product
    1. Includes ProductType and Technical Detail (which are hidden)
    2. Includes upfront information such as Manufacturer, Series, Model
2.  Product Type consists detailed information about use types, applications, mounting locations and such
3.	Techinical Detail consists of numerical information about the tech of the product. 

Product has one to one relationship with both product type and technical detail
