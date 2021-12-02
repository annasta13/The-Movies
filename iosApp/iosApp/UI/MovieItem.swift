//
//  MovieItem.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct MovieItem: View {
    var movie: Movie
    @ObservedObject var imageLoader = ImageLoaderService()
    @State var image: UIImage = UIImage()
    
    var body: some View {
        let defaultImage = UIImage(named: "default_image")
        
        ZStack{
            RoundedRectangle(cornerRadius: 5, style: .continuous).fill(Color.white).shadow(radius: 3)
            VStack(alignment: .leading, spacing: nil, content: {
                HStack{
                    Image(uiImage: image)
                        .resizable()
                        .clipped()
                        .aspectRatio(contentMode: .fit)
                        //.scaledToFill()
                        .frame(width: 130, height: 130)
                        .onReceive(imageLoader.$image) { image in
                            if (image.isImage()) {self.image = image}
                            else { self.image = defaultImage!}
                        }
                        .onAppear {
                            imageLoader.loadImage(for: movie.poster)
                        }
                    
                    VStack{
                        HStack{Text(movie.originalTitle).font(.body).bold()
                            Spacer()
                        }
                        HStack{
                            Text(movie.overview).font(.body).padding(.top, 12)
                            Spacer()
                        }
                    }.frame(height: 130)
                }
            }).padding()
        }
        ///zstack
        .padding(.horizontal)
    }
}

struct MovieItem_Previews: PreviewProvider {
    static var previews: some View {
        MovieItem(movie: Movie.companion.mocked)
    }
}
