package william.miranda.frescotest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

class MainActivity : AppCompatActivity() {

    private val button by lazy { findViewById<Button>(R.id.button) }
    private val draweeView by lazy { findViewById<SimpleDraweeView>(R.id.draweeView) }

    private val initialImage = "https://picsum.photos/id/197/200/300.jpg"
    private val finalImageLowRes = "https://picsum.photos/id/197/400/600.jpg"
    private val finalImageHighRes = "https://picsum.photos/id/197/1000/1500.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Load the Starting Image
        draweeView.setImageURI(
            Uri.parse(initialImage)
        )

        button.setOnClickListener {
            //Update the Images
            updateImage()
        }
    }

    private fun updateImage() {

        val controller = Fresco.newDraweeControllerBuilder()
            .setOldController(draweeView.controller)
            .setLowResImageRequest(
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(finalImageLowRes))
                    .disableDiskCache().build()
            )
            .setImageRequest(
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(finalImageHighRes))
                    .disableDiskCache().build()
            )
            .setRetainImageOnFailure(true)
            .build()

        draweeView.controller = controller
    }
}