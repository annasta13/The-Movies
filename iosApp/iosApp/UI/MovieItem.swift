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
    @StateObject private var loader: ImageLoader
    
    init(movie: Movie){
        self.movie = movie
        _loader = StateObject(wrappedValue: ImageLoader(url: movie.poster.asUrl(), cache: Environment(\.imageCache).wrappedValue))
    }
    
    var body: some View {
        
        ZStack{
            RoundedRectangle(cornerRadius: 5, style: .continuous).fill(Color.white).shadow(radius: 3)
            VStack(alignment: .leading, spacing: nil, content: {
                HStack{
                    
                    if loader.image != nil {
                        Image(uiImage: loader.image!).resizable().clipped()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 130, height: 130)
                    } else {
                        Image("default_image").resizable().clipped()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 130, height: 130)
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
        } .onAppear(perform: {loader.load()})
        ///zstack
        .padding(.horizontal)
    }
}

struct MovieItem_Previews: PreviewProvider {
    static var previews: some View {
        MovieItem(movie: Movie.companion.mocked)
    }
}
