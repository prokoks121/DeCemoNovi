package com.example.decemo.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.renderscript.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.example.decemo.R
import com.example.decemo.model.Dogadjaj
import com.example.decemo.ui.epoxy.model.*
import com.example.decemo.ui.viewmodel.StoryViewModel
import java.security.MessageDigest

private const val DEFAULT_DOWN_SAMPLING = 0.5f

class StoryFragment : Fragment() {

    private lateinit var viewModel:StoryViewModel
    private val data:StoryFragmentArgs by navArgs()
    private lateinit var listaDogadjaja:ArrayList<Dogadjaj>
    private lateinit var dogadjaj:Dogadjaj
    private lateinit var controller:StoryEpoxyController
    private lateinit var epoxyRecyclerView: EpoxyRecyclerView


    private var position:Int = 0

    private val URL = "https://api.polovnitelefoni.net/slike/"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(StoryViewModel::class.java)
        viewModel.dogadjaj.postValue(data.Dogadjaj.toList() as ArrayList<Dogadjaj>?)
        viewModel.positon.postValue(data.Position)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.scrollToPositionWithOffset(2,50)
        epoxyRecyclerView = view.findViewById(R.id.storyEpoxyRecyclerView)
        epoxyRecyclerView.layoutManager = layoutManager

        controller = StoryEpoxyController()
        epoxyRecyclerView.setController(controller)

        viewModel.dogadjaj.observe(viewLifecycleOwner, Observer {
            listaDogadjaja = it
            val lista =  ArrayList<ArrayList<Dogadjaj>>()
            lista.add(arrayListOf<Dogadjaj>(it[0]))
            lista.add(arrayListOf<Dogadjaj>(it[1],it[2],it[3]))
            lista.add(arrayListOf<Dogadjaj>(it[4],it[5]))

            controller.setData(Data(lista,requireActivity()))
        })


        viewModel.positon.observe(viewLifecycleOwner, Observer {
            position = it
            dogadjaj = listaDogadjaja.get(it)
        })
    }


    class BlurTransformation(private val context: Context) : BitmapTransformation() {

        override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
            val source: Bitmap = toTransform
            val scaledWidth = (source.width * DEFAULT_DOWN_SAMPLING).toInt()
            val scaledHeight = (source.height * DEFAULT_DOWN_SAMPLING).toInt()
            val bitmap = pool[scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888]
            return BitmapResource.obtain(this.blurBitmap(context, source, bitmap, Color.argb(90, 255, 255, 255)), pool)?.get()
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {
            messageDigest.update("blur transformation".toByteArray())
        }

        @Synchronized
        fun blurBitmap(context: Context, source: Bitmap?, bitmap: Bitmap, @ColorInt colorOverlay: Int): Bitmap? {
            if (source == null) return bitmap
            Canvas(bitmap).apply {
                scale(DEFAULT_DOWN_SAMPLING, DEFAULT_DOWN_SAMPLING)
                drawBitmap(source, 0f, 0f, Paint().apply {
                    flags = Paint.FILTER_BITMAP_FLAG
                })
                drawColor(colorOverlay)
            }
            return try {
                blur(context, bitmap)
            } catch (e: RSRuntimeException) {
                e.printStackTrace()
                bitmap
            }
        }

        @Throws(RSRuntimeException::class)
        private fun blur(context: Context, bitmap: Bitmap): Bitmap {
            var rs: RenderScript? = null
            var input: Allocation? = null
            var output: Allocation? = null
            var blur: ScriptIntrinsicBlur? = null
            try {
                rs = RenderScript.create(context)
                rs.messageHandler = RenderScript.RSMessageHandler()
                input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
                output = Allocation.createTyped(rs, input.type)
                blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)).apply {
                    setInput(input)
                    setRadius(25f)
                    forEach(output)
                }
                output.copyTo(bitmap)
            } finally {
                rs?.destroy()
                input?.destroy()
                output?.destroy()
                blur?.destroy()
            }
            return bitmap
        }
    }
    data class Data(
            var dogadjaj: ArrayList<ArrayList<Dogadjaj>>,
            var activity: Activity,
    var recyclerView: EpoxyRecyclerView
    )

    class StoryEpoxyController:TypedEpoxyController<Data>(){




        override fun buildModels(data: Data?) {

            var i =0
            data?.let {
                it.dogadjaj.forEach {dog->
                    storyView {
                        id(i++)
                        context(data.activity)
                        dogadjaj(dog)
                    }
                }
            }

        }

    }

}