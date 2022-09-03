package com.example.a7minutesworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        // Jumping Jacks
        val jumpingJacks = ExerciseModel(1, "Jumping Jacks", R.drawable.ic_jumping_jacks, false, false)
        exerciseList.add(jumpingJacks)

        //Wall Sit
        val wallSit = ExerciseModel(2, "Wall Sit", R.drawable.ic_wall_sit, false, false)
        exerciseList.add(wallSit)

        //Triceps dip on chair
        val tricepsChair = ExerciseModel(3, "Triceps dip on chair", R.drawable.ic_triceps_dip_on_chair, false, false)
        exerciseList.add(tricepsChair)

        //Step up on to chair
        val stepUpChair = ExerciseModel(4, "Step up on to chair", R.drawable.ic_step_up_onto_chair, false, false)
        exerciseList.add(stepUpChair)

        //Squat
        val squat = ExerciseModel(5, "Squat", R.drawable.ic_squat, false, false)
        exerciseList.add(squat)

        //Side plank
        val sidePlank = ExerciseModel(6, "Side Plank", R.drawable.ic_side_plank, false, false)
        exerciseList.add(sidePlank)

        //Push up and rotation
        val pushUpRotation = ExerciseModel(7, "Push up and rotation", R.drawable.ic_push_up_and_rotation, false, false)
        exerciseList.add(pushUpRotation)

        //Push up
        val pushUp = ExerciseModel(8, "Push up", R.drawable.ic_push_up, false, false)
        exerciseList.add(pushUp)

        //Plank
        val plank = ExerciseModel(9, "Plank", R.drawable.ic_plank, false, false)
        exerciseList.add(plank)

        //Lunge
        val lunge = ExerciseModel(10, "Lunge", R.drawable.ic_lunge, false, false)
        exerciseList.add(lunge)

        //High knees running in place
        val highKneesRunningInPlace = ExerciseModel(11, "High knees running in place", R.drawable.ic_high_knees_running_in_place, false, false)
        exerciseList.add(highKneesRunningInPlace)

        //Abdominal Crunch
        val abdominalCrunch = ExerciseModel(12, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
        exerciseList.add(abdominalCrunch)

        return exerciseList
    }
}