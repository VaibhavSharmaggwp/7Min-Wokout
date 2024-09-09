package com.example.a7minworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val basic_abdominal_crunch = ExerciseModel(
            2,
            "basic_abdominal_crunch",
            R.drawable.basic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(basic_abdominal_crunch)
        val high_knees_sprints = ExerciseModel(
            3,
            "high_knees_sprints",
            R.drawable.high_knee_sprints,
            false,
            false
        )
        exerciseList.add(high_knees_sprints)

        val lunges = ExerciseModel(
            4,
            "lunges",
            R.drawable.lunges,
            false,
            false
        )
        exerciseList.add(lunges)

        val plank = ExerciseModel(
            5,
            "plank",
            R.drawable.plank,
            false,
            false
        )
        exerciseList.add(plank)
        val push_up = ExerciseModel(
            6,
            "push_up",
            R.drawable.pushup,
            false,
            false
        )
        exerciseList.add(push_up)

        val push_up_rotation = ExerciseModel(
            7,
            "push_up_rotation",
            R.drawable.push_up_rotation,
            false,
            false
        )
        exerciseList.add(push_up_rotation)

        val side_plank = ExerciseModel(
            8,
            "side_plank",
            R.drawable.side_plank,
            false,
            false
        )
        exerciseList.add(side_plank)

        val squat = ExerciseModel(
            9,
            "squat",
            R.drawable.squat,
            false,
            false
        )
        exerciseList.add(squat)

        val step_up_on_chair = ExerciseModel(
            10,
            "step_up_on_chair",
            R.drawable.step_up_on_chair,
            false,
            false
        )
        exerciseList.add(step_up_on_chair)

        val tricepdip = ExerciseModel(
            11,
            "tricepdip",
            R.drawable.tricep_dip,
            false,
            false
        )
        exerciseList.add(tricepdip)

        val wall_sit = ExerciseModel(
            12,
            "wall_sit",
            R.drawable.wall_sit,
            false,
            false
        )
        exerciseList.add(wall_sit)



        return exerciseList
    }
}