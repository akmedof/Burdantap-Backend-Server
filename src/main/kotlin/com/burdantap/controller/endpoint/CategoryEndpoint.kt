package com.burdantap.controller.endpoint

sealed class CategoryEndpoint(val path: String){
    data object CreateCategory : CategoryEndpoint("category/create/category")
    data object CreateSubcategory : CategoryEndpoint("category/create/subcategory")
    data object CreateSubSubcategory : CategoryEndpoint("category/create/sub-subcategory")
    data object ReadCategory : CategoryEndpoint("category/read/categories")
    data object ReadMainCategory : CategoryEndpoint("category/read/main-categories")
    data object ReadSubCategory : CategoryEndpoint("category/read/sub-categories")
}
