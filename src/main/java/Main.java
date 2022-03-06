import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {
    private int width, height;
    private String title;
    private long glfwWindow;

    private Main(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!?!");

        init();
        loop();

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // free!!
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to init glfw");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
//		glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create glfw window");
        }

        glfwMakeContextCurrent(glfwWindow);

        glfwSwapInterval(1); //수직동기화 활성화

        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }

    public void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();

            glClearColor(1f, 1f, 1f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

//            for (float i = 10f; i<101f;i+=10f) {
                glLoadIdentity();
                glRotatef((float) glfwGetTime() * 50, 1f, 1f, 1f);

                glBegin(GL_POLYGON);
                glColor3f(1f, 0f, 0f);
                glVertex3f(-0.5f, 0.5f, 0.5f);
                glVertex3f(-0.5f, 0.5f, -0.5f);
                glVertex3f(0.5f, 0.5f, -0.5f);
                glVertex3f(0.5f, 0.5f, 0.5f);
                glEnd();

                glBegin(GL_POLYGON);
                glColor3f(1f, 217f/255f, 0f);
                glVertex3f(0.5f, -0.5f, 0.5f);
                glVertex3f(-0.5f, -0.5f, 0.5f);
                glVertex3f(-0.5f, 0.5f, 0.5f);
                glVertex3f(0.5f, 0.5f, 0.5f);
                glEnd();

                glBegin(GL_POLYGON);
                glColor3f(1f, 1f, 0f);
                glVertex3f(0.5f, 0.5f, -0.5f);
                glVertex3f(0.5f, -0.5f, -0.5f);
                glVertex3f(0.5f, -0.5f, 0.5f);
                glVertex3f(0.5f, 0.5f, 0.5f);
                glEnd();

                glBegin(GL_POLYGON);
                glColor3f(1f, 1f, 0f);
                glVertex3f(-0.5f, 0.5f, 0.5f);
                glVertex3f(-0.5f, 0.5f, -0.5f);
                glVertex3f(-0.5f, -0.5f, -0.5f);
                glVertex3f(-0.5f, -0.5f, 0.5f);
                glEnd();

                glBegin(GL_POLYGON);
                glColor3f(0f, 0f, 1f);
                glVertex3f(-0.5f, -0.5f, 0.5f);
                glVertex3f(0.5f, -0.5f, 0.5f);
                glVertex3f(0.5f, -0.5f, -0.5f);
                glVertex3f(-0.5f, -0.5f, -0.5f);
                glEnd();

                glBegin(GL_POLYGON);
                glColor3f(139f/255f, 0f, 1f);
                glVertex3f(0.5f, -0.5f, -0.5f);
                glVertex3f(-0.5f, -0.5f, -0.5f);
                glVertex3f(-0.5f, 0.5f, -0.5f);
                glVertex3f(0.5f, 0.5f, -0.5f);
                glEnd();
//            }

            glfwSwapBuffers(glfwWindow);
        }
    }

    public static void main(String[] args) {
        new Main(500, 500, "many cubes").run();
    }
}