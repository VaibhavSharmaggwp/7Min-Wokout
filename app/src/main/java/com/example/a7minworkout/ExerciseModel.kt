package com.example.a7minworkout

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
) {
    // Getter and Setter for 'id'
    fun getId(): Int {
        return id
    }
    fun setId(id: Int) {
        this.id = id
    }

    // Getter and Setter for 'name'
    fun getName(): String {
        return name
    }
    fun setName(name: String) {
        this.name = name
    }

    // Getter and Setter for 'image'
    fun getImage(): Int {
        return image
    }
    fun setImage(image: Int) {
        this.image = image
    }

    // Getter and Setter for 'isCompleted'
    fun getIsCompleted(): Boolean {
        return isCompleted
    }
    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
    }

    // Getter and Setter for 'isSelected'
    fun getIsSelected(): Boolean {
        return isSelected
    }
    fun setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }
}
