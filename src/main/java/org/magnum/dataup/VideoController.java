/*
 *
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class VideoController {

    /**
     * You will need to create one or more Spring controllers to fulfill the
     * requirements of the assignment. If you use this file, please rename it
     * to something other than "AnEmptyController"
     *
     *
     ________  ________  ________  ________          ___       ___  ___  ________  ___  __
     |\   ____\|\   __  \|\   __  \|\   ___ \        |\  \     |\  \|\  \|\   ____\|\  \|\  \
     \ \  \___|\ \  \|\  \ \  \|\  \ \  \_|\ \       \ \  \    \ \  \\\  \ \  \___|\ \  \/  /|_
     \ \  \  __\ \  \\\  \ \  \\\  \ \  \ \\ \       \ \  \    \ \  \\\  \ \  \    \ \   ___  \
     \ \  \|\  \ \  \\\  \ \  \\\  \ \  \_\\ \       \ \  \____\ \  \\\  \ \  \____\ \  \\ \  \
     \ \_______\ \_______\ \_______\ \_______\       \ \_______\ \_______\ \_______\ \__\\ \__\
     \|_______|\|_______|\|_______|\|_______|        \|_______|\|_______|\|_______|\|__| \|__|

     *
     */

    private List<Video> videos = new ArrayList<>();

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public @ResponseBody Collection<Video> getVideoList(){
        return videos;
    }

    @RequestMapping(value = "/video", method = RequestMethod.POST)
    public @ResponseBody Video addVideo(@RequestBody Video v){
        v.setId(1);
        v.setDataUrl("");
        return v;
    }

    @RequestMapping(value = "/video/{id}/data", method = RequestMethod.POST)
    public @ResponseBody VideoStatus setVideoData(@PathVariable("id") long id,
                                                  @RequestParam("data") MultipartFile videoData){
        return new VideoStatus(VideoStatus.VideoState.READY);
    }

    @RequestMapping(value = "/video/{id}/data", method = RequestMethod.GET)
    public void getData(@PathVariable long id,
                        HttpServletResponse response,
                        HttpServletRequest request){

    }

    private String getUrlBaseForLocalServer() {
       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       return "http://"+request.getServerName()+((request.getServerPort() != 80) ? ":"+request.getServerPort() : "");
    }
}
