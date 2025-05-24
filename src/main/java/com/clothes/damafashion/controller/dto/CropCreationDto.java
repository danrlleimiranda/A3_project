package com.clothes.damafashion.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.clothes.damafashion.entity.Product;

import java.time.LocalDate;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(String name, Double plantedArea, LocalDate plantedDate,
                              LocalDate harvestDate) {


  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Product toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
