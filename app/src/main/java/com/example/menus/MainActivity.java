package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActionMode accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.hw));

        Button boton = findViewById(R.id.tocame);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accion==null)
                {
                    accion = MainActivity.this.startActionMode(new accion());
                    v.setSelected(true);

                }
                else
                {
                    accion.finish();
                    accion=null;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sun: {
                Toast.makeText(getApplicationContext(), "hace calor", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.cloud: {
                Toast.makeText(getApplicationContext(), "no veo nada", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.ice: {
                Toast.makeText(getApplicationContext(), "Hace friiio", Toast.LENGTH_LONG).show();
                break;
            }

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.premio: {
                Toast.makeText(getApplicationContext(), "Felicidades, ganaste 1000$", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.trampa: {
                Toast.makeText(getApplicationContext(), "Por no seguir las reglas se te han robado todos tus datos bancarios", Toast.LENGTH_SHORT).show();
                break;
            }

        }
        return true;
    }

    public class accion implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.accion, menu);
            accion=mode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId())
            {
                case (R.id.LoveMe):
                {
                    Toast.makeText(getApplicationContext(),"pretend that you love me", Toast.LENGTH_SHORT).show();
                    break;
                }
                case (R.id.nose):
                {
                    View nose = findViewById(R.id.nose);
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(),nose);
                    popupMenu.getMenuInflater().inflate(R.menu.popy,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId())
                            {
                                case (R.id.TTI):
                                {
                                    Toast.makeText(getApplicationContext(),"Me caes bien", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                case (R.id.yosi):
                                {
                                    Toast.makeText(getApplicationContext(),"no me caes", Toast.LENGTH_SHORT).show();
                                    break;
                                }

                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }

            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            accion=null;
        }
    }

}