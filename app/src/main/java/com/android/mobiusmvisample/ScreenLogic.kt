package drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.android.mobiusmvisample.ScreenEffect
import com.android.mobiusmvisample.ScreenEvent
import com.android.mobiusmvisample.ScreenModel
import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update
import java.io.IOException
import java.io.InputStream


class ScreenLogic(val context: Context) : Update<ScreenModel, ScreenEvent, ScreenEffect>, IScreenLogic {
    private val folderName = "dogs"

    override fun update(model: ScreenModel, event: ScreenEvent): Next<ScreenModel, ScreenEffect> = when (event) {
        is ScreenEvent.Loading -> next<ScreenModel, ScreenEffect>(model.copy(isLoading = true))
        is ScreenEvent.UploadData -> next<ScreenModel, ScreenEffect>(model.copy(isLoading = false, data = getImages()))
    }

    override fun getImages(): List<Bitmap> {
        return getImagesName().map { getBitmap(it) }
    }

    @Throws(IOException::class)
    private fun getImagesName(): List<String> {
        val assetManager = context.assets
        val files = assetManager.list(folderName)
        return files.toList()
    }

    private fun getBitmap(strName: String): Bitmap {
        val assetManager = context.assets
        var istr: InputStream? = null
        try {
            istr = assetManager.open(folderName + "/" + strName)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return BitmapFactory.decodeStream(istr)
    }

}