package com.arp.practicaeditorimagen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

public class Principal extends AppCompatActivity {

    private ImageView iv;
    private File f;
    private Bitmap bitmap,bitmapOriginal;
    private Uri data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ini();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bitmap", bitmap);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bitmap=savedInstanceState.getParcelable("bitmap");
        actualizar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.giroIzq:
                bitmap=Editor.rotarBitmap(bitmap,-90);
                actualizar();
                return true;
            case R.id.giroDer:
                bitmap=Editor.rotarBitmap(bitmap,90);
                actualizar();
                return true;
            case R.id.gris:
                bitmap=Editor.toEscalaDeGris(bitmap);
                actualizar();
                return true;
            case R.id.original:
                bitmap=bitmapOriginal;
                actualizar();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ini(){
        data = getIntent().getData();
        iv=(ImageView)findViewById(R.id.imageView);
        f = new File(data.getPath());
        BitmapFactory.Options opciones = new BitmapFactory.Options();
        opciones.inJustDecodeBounds = true;
        int anchoFoto = opciones.outWidth;
        int altoFoto = opciones.outHeight;
        int factorEscalado =Editor.factorDeEscalado(anchoFoto,altoFoto,iv.getMaxWidth(),iv.getMaxHeight());
        opciones.inJustDecodeBounds = false;
        opciones.inSampleSize = factorEscalado;
        opciones.inPurgeable = true;
        bitmapOriginal = BitmapFactory.decodeFile(f.getAbsolutePath(), opciones);
        bitmap=bitmapOriginal;
        iv.setImageBitmap(bitmap);
    }

    private void actualizar(){
        iv.setImageBitmap(bitmap);
    }
}
