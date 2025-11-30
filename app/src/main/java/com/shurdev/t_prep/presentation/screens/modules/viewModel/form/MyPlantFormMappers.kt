//package com.shurdev.t_prep.presentation.screens.modules.viewModel.form
//
//import com.shurdev.domain.models.myPlant.MyPlant
//import com.shurdev.domain.models.myPlant.MyPlantData
//
//fun MyPlant.toForm(): MyPlantForm {
//    return MyPlantForm(
//        name = this.name,
//        imageData = this.imageData,
//        watering = this.plantWatering,
//        otherInfo = this.otherInfo,
//    )
//}
//
//fun MyPlantForm.toData(): MyPlantData {
//    return MyPlantData(
//        name = this.name,
//        imageData = this.imageData,
//        plantWatering = this.watering,
//        otherInfo = this.otherInfo,
//    )
//}