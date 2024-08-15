package com.example.testapplicationcell

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapplicationcell.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = CellRecyclerViewAdapter()

        binding.rvCell.adapter = adapter

        val listOfCells = mutableListOf<Cell>()

        var id = 0

        val listOfLiveCells = mutableListOf<Cell>()

        val listOfDeadCells = mutableListOf<Cell>()

        var previous: String

        binding.create.setOnClickListener {
            var cell = cell(id)

            if (listOfLiveCells.size > 2) {
                cell = Cell(
                    id = id,
                    image = R.drawable.borned,
                    title = "Жизнь",
                    titleSecond = "Ку-ку!"
                )
            }
            if (listOfDeadCells.size > 2) {
                listOfCells.remove(listOfCells.lastOrNull {
                    it.title == "Жизнь"
                })
            }

            previous = cell.title

            listOfCells.add(cell)

            adapter.submitList(listOfCells.toList())

            liveCheck(previous, cell, listOfLiveCells)
            deadCheck(previous, cell, listOfDeadCells)
            id++
        }

    }


    private fun deadCheck(
        previous: String,
        cell: Cell,
        listOfDeadCells: MutableList<Cell>
    ) {
        if (previous == cell.title && cell.title == "Мёртвая") {
            listOfDeadCells.add(cell)
        } else if (previous!="Мёртвая") {
            listOfDeadCells.clear()
        }
    }

    private fun liveCheck(
        previous: String,
        cell: Cell,
        listOfLiveCells: MutableList<Cell>
    ) {
        if (previous == cell.title && cell.title == "Живая") {
            listOfLiveCells.add(cell)
        } else if (previous!="Живая") {
            listOfLiveCells.clear()
        }
    }


    private fun cell(id: Int): Cell {
        val cell = when ((0..1).random()) {
            0 -> {
                Cell(
                    id = id,
                    image = R.drawable.explosion,
                    title = "Живая",
                    titleSecond = "и шевелится!"
                )

            }

            1 -> {
                Cell(
                    id = id,
                    image = R.drawable.skull,
                    title = "Мёртвая",
                    titleSecond = "или прикидывается."
                )

            }

            else -> null
        } ?: throw RuntimeException("Бред")
        return cell
    }
}