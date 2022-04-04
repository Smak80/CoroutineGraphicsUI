package ru.smak

import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.GroupLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JTextField
import kotlin.random.Random

class Window : JFrame() {

    private val tf: JTextField
    private val lbl: JLabel

    init{
        defaultCloseOperation = EXIT_ON_CLOSE

        lbl = JLabel()
        lbl.text = " "

        tf = JTextField()
        tf.addKeyListener(object: KeyAdapter(){
            override fun keyTyped(e: KeyEvent?) {
                super.keyTyped(e)
                lbl.text = tf.text + e?.keyChar
                if (lbl.text.uppercase() == "START"){
                    start()
                }
            }
        })

        layout = GroupLayout(contentPane).apply {

            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(8)
                    .addGroup(
                        createParallelGroup()
                        .addComponent(tf, 400, MAX_SZ, MAX_SZ)
                        .addComponent(lbl, 400, MAX_SZ, MAX_SZ)
                    )
                    .addGap(8)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(8)
                    .addComponent(tf, MIN_SZ, MIN_SZ, MIN_SZ)
                    .addGap(4)
                    .addComponent(lbl, MIN_SZ, MIN_SZ, MIN_SZ)
                    .addGap(8)
            )
        }
        pack()
    }

    private fun start() {
        lbl.text = "STARTED"
        val d = CoroutineScope(Dispatchers.Swing).async {
            delay(5000)
            Random.nextInt(1000)
        }
        CoroutineScope(Dispatchers.Swing).launch{
            val res = d.await()
            lbl.text = "RESULT=$res"
        }
    }

    companion object{
        const val MAX_SZ = GroupLayout.DEFAULT_SIZE
        const val MIN_SZ = GroupLayout.PREFERRED_SIZE
    }
}